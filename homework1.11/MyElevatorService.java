package elevator;

import elevator.api.*;
import elevator.impl.cmd.ElevatorCore;

public class MyElevatorService extends ElevatorService {

    private final int countOfFloors;
    private String displayLine;
    private boolean[] callsOnFloors;
    private boolean[] callsUp;
    private boolean[] callsDown;
    private boolean[] callsInside;

    private boolean isAirConditioningEnabled = false;
    private RunningStatus currentStatus;
    private long delayDoors;
    public MyElevatorService(int countOfFloors) {
        this.countOfFloors = countOfFloors;
        callsInside = new boolean[countOfFloors + 1];
        callsDown = new boolean[countOfFloors + 1];
        callsUp = new boolean[countOfFloors + 1];
        callsOnFloors = new boolean[countOfFloors + 1];
        currentStatus = RunningStatus.WAITING;
    }

    @Override
    public void handleInsideKeyPress(InsideKeyInput info) {
        if(info.getType() == InsideKeyType.FLOOR_NUMBER) {
            callsInside[info.getFloorNumber()] = true;
        } else if(info.getType() == InsideKeyType.SIGNAL_DOOR_OPEN) {
            ElevatorCore elevator = coreModule.getElevator(0);
            if(elevator.getElevatorState() == ElevatorState.WAITING_CLOSED || elevator.getElevatorState() == ElevatorState.DOORS_CLOSING) {
                currentStatus = RunningStatus.OPENING_DOORS;
                elevator.openDoors();
            }
        } else if(info.getType() == InsideKeyType.SIGNAL_DOOR_CLOSE) {
            ElevatorCore elevator = coreModule.getElevator(0);
            if(elevator.getElevatorState() == ElevatorState.WAITING_OPENED || elevator.getElevatorState() == ElevatorState.DOORS_OPENING) {
                currentStatus = RunningStatus.CLOSING_DOORS;
                elevator.closeDoors();
            }
        } else if(info.getType() == InsideKeyType.AC_CHANGE) {
            ElevatorCore elevator = coreModule.getElevator(0);
            isAirConditioningEnabled = !isAirConditioningEnabled;
            elevator.setAirConditioningEnabled(isAirConditioningEnabled);
        }
        ElevatorCore elevator = coreModule.getElevator(0);
        displayLine = String.valueOf(elevator.getElevatorFloor());
        elevator.setStringOnDisplay(displayLine);
    }

    @Override
    public void handleOutsideKeyPress(OutsideKeyInput info) {
        if(info.getDirection() == OutsideKeyDirection.CALL) {
            callsOnFloors[info.getFloor()] = true;
        }
        if(info.getDirection() == OutsideKeyDirection.UP) {
            callsUp[info.getFloor()] = true;
        } else if (info.getDirection() == OutsideKeyDirection.DOWN) {
            callsDown[info.getFloor()] = true;
        }
    }


    @Override
    protected void loop() {
        ElevatorCore elevator = coreModule.getElevator(0);
        displayLine = String.valueOf(elevator.getElevatorFloor());
        elevator.setStringOnDisplay(displayLine);
        if(currentStatus == RunningStatus.WAITING) {
            // Проверяем вызовы на текущем этаже
            if(callsUp[elevator.getElevatorFloor()] || callsDown[elevator.getElevatorFloor()] || callsInside[elevator.getElevatorFloor()] || callsOnFloors[elevator.getElevatorFloor()]) {
                currentStatus = RunningStatus.OPENING_DOORS;
                elevator.openDoors();
                callsUp[elevator.getElevatorFloor()] = false;
                callsDown[elevator.getElevatorFloor()] = false;
                callsInside[elevator.getElevatorFloor()] = false;
                callsOnFloors[elevator.getElevatorFloor()] = false;
                return;
            }
            for(int i = elevator.getElevatorFloor(); i <= countOfFloors; i++) {
                if(callsUp[i] || callsInside[i] || callsOnFloors[i] || callsDown[i]) {
                    currentStatus = RunningStatus.MOVING_UP;
                    return;
                }
            }

            for (int i = elevator.getElevatorFloor(); i >= 0; i--) {
                if (callsDown[i] || callsInside[i] || callsUp[i] || callsOnFloors[i]) {
                    currentStatus = RunningStatus.MOVING_DOWN;
                    return;
                }
            }
        } else if(currentStatus == RunningStatus.MOVING_UP) {
            boolean flag = false;
            for(int i = elevator.getElevatorFloor(); i <= countOfFloors; i++) {
                if(callsUp[i] || callsInside[i] || callsOnFloors[i]) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                currentStatus = RunningStatus.WAITING;
                return;
            }
            if(elevator.getElevatorState() == ElevatorState.WAITING_CLOSED) {
                if(callsUp[elevator.getElevatorFloor()] || callsInside[elevator.getElevatorFloor()] || callsOnFloors[elevator.getElevatorFloor()]) {
                    callsUp[elevator.getElevatorFloor()] = false;
                    callsInside[elevator.getElevatorFloor()] = false;
                    callsOnFloors[elevator.getElevatorFloor()] = false;
                    currentStatus = RunningStatus.OPENING_DOORS;
                    elevator.openDoors();
                    return;
                }

                elevator.moveToFloor(elevator.getElevatorFloor() + 1);
            }
        } else if(currentStatus == RunningStatus.MOVING_DOWN) {
            boolean flag = false;
            for (int i = elevator.getElevatorFloor(); i >= 0; i--) {
                if (callsDown[i] || callsInside[i] || callsOnFloors[i]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                currentStatus = RunningStatus.WAITING;
                return;
            }

            if (elevator.getElevatorState() == ElevatorState.WAITING_CLOSED) {
                if (callsDown[elevator.getElevatorFloor()] || callsInside[elevator.getElevatorFloor()] || callsOnFloors[elevator.getElevatorFloor()]) {
                    callsDown[elevator.getElevatorFloor()] = false;
                    callsInside[elevator.getElevatorFloor()] = false;
                    callsOnFloors[elevator.getElevatorFloor()] = false;
                    currentStatus = RunningStatus.OPENING_DOORS;
                    elevator.openDoors();
                    return;
                }

                elevator.moveToFloor(elevator.getElevatorFloor() - 1);
            }
        } else if(currentStatus == RunningStatus.OPENING_DOORS) {
            if(elevator.getElevatorState() == ElevatorState.WAITING_OPENED) {
                if(delayDoors == 0) {
                    delayDoors = System.currentTimeMillis();
                } else if(System.currentTimeMillis() - delayDoors > 3000) {
                    currentStatus = RunningStatus.CLOSING_DOORS;
                    elevator.closeDoors();
                    delayDoors = 0;
                }
            }
        } else if(currentStatus == RunningStatus.CLOSING_DOORS) {
            if (elevator.getElevatorState() == ElevatorState.WAITING_CLOSED) {
                currentStatus = RunningStatus.WAITING;
                delayDoors = 0;
            }
        }
    }
}

