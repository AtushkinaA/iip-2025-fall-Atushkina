package homework2510;

public abstract class Animal {
    String title;
    String environment;
    String whatBloods;
    String bodyStructure;
    String haveSpine;
    String typeEating;

    public Animal(String title, String environment, String whatBloods, String bodyStructure, String haveSpine, String typeEating) {
    }

    public String getTitle() {
        return title;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getWhatBloods() {
        return whatBloods;
    }

    public String getBodyStructure() {
        return bodyStructure;
    }

    public String getHaveSpine() {
        return haveSpine;
    }

    public String getTypeEating() {
        return typeEating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setHaveSpine(String haveSpine) {
        this.haveSpine = haveSpine;
    }

    public void setTypeEating(String typeEating) {
        this.typeEating = typeEating;
    }

    public void setBodyStructure(String bodyStructure) {
        this.bodyStructure = bodyStructure;
    }

    public void setWhatBloods(String whatBloods) {
        this.whatBloods = whatBloods;
    }

    void say() {
    }
}


