package technology.steinhauer.demo.valtech.entities;

/**
 * Represents a follower-followee relationship, needed for following scenario.
 */
public class Follower {

    private int id;
    private String follower;
    private String followee;

    public Follower() {}

    public Follower(String follower, String followee) {
        this.followee = followee;
        this.follower = follower;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowee() {
        return followee;
    }

    public void setFollowee(String followee) {
        this.followee = followee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Follower follower1 = (Follower) o;

        if (id != follower1.id) return false;
        if (!followee.equals(follower1.followee)) return false;
        if (!follower.equals(follower1.follower)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + follower.hashCode();
        result = 31 * result + followee.hashCode();
        return result;
    }
}
