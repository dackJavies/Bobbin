package text_engine.boundaries;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

import text_engine.items.Key;

/**
 * Represents a door from one {@link Door} to another
 */
public class Door implements Serializable {

    private final long lock;
    private final Room room1;
    private final Room room2;
    private boolean locked;

    /**
     * Constructs a {@link Door} between the 2 provided rooms.
     *
     * @param locked whether this {@link Door} is locked
     * @param room1  the room on one side of the {@link Door}
     * @param room2  the room on the other side of the {@link Door}
     */
    public Door(boolean locked, Room room1, Room room2) {
        this.locked = locked;
        lock = new Random().nextLong();
        Objects.requireNonNull(room1);
        Objects.requireNonNull(room2);

        this.room1 = room1;
        this.room2 = room2;

        room1.addExits(this);
        room2.addExits(this);
    }

    public Room getRoom1() {
        return room1;
    }

    public Room getRoom2() {
        return room2;
    }

    /**
     * Access the room on the other side of the {@link Door}
     *
     * @param from the room you're coming from
     * @return the room on the other side
     */
    public Room getOtherRoom(Room from) {
        Objects.requireNonNull(from);
        if (!locked) {
            if (from.equals(this.room1)) {
                return this.room2;
            }
            else if (from.equals(this.room2)) {
                return this.room1;
            }
            else {
                throw new IllegalArgumentException("Given room is not connected to this door");
            }
        }
        else {
            throw new IllegalStateException("Door is locked.");
        }
    }

    /**
     * Generate a description of the {@link Door} based on the rooms it connects.
     *
     * @return The generated description.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (room1 != null && room2 != null) {
            result.append("Door between ")
                  .append(room1.getName())
                  .append(" and ")
                  .append(room2.getName());
        }
        else if (room1 != null ^ room2 != null) {
            result.append("Dead-end door connected to ");
            result.append(room1 != null ? room1 : room2);
        }
        else {
            result.append("Roomless door");
        }

        if (locked) {
            result.append(" (locked)");
        }
        result.append(".");

        return result.toString();
    }


    /**
     * Tries to unlock this {@link Door} with the given {@link Key}.
     *
     * @param key the key used to unlock the {@link Door}
     * @return {@code true} if successfully unlocked, {@code false} otherwise
     */
    public boolean unlock(Key key) {
        return setLocked(false, key);
    }

    /**
     * Tries to lock this {@link Door} with the given {@link Key}.
     *
     * @param key the key used to lock the {@link Door}
     * @return {@code true} if successfully lock, {@code false} otherwise
     */
    public boolean lock(Key key) {
        return setLocked(true, key);
    }

    /**
     * Tries to change the lock of this {@link Door} with the given {@link Key}.
     *
     * @param key        the key used to lock the {@link Door}
     * @param toBeLocked whether the door is to be locked or unlocked
     * @return {@code true} if successfully lock, {@code false} otherwise
     */
    public boolean setLocked(boolean toBeLocked, Key key) {
        if (fits(key)) {
            this.locked = toBeLocked;
            return true;
        }
        return false;
    }


    /**
     * Makes a key that fits this {@link Door};
     *
     * @param name        name of the {@link Key}
     * @param description description of the {@link Key}
     * @return key that fits this {@link Door}
     */
    public Key makeKey(String name, String description) {
        return new Key(name, description, Long.hashCode(lock));
    }

    public boolean fits(Key key) {
        return Objects.requireNonNull(key).hashCode() == Long.hashCode(lock);
    }

    public boolean isLocked() {
        return locked;
    }

    @Override
    public boolean equals(Object obj) {
        // Basing equality on hashCode(), which is a little dirty, but it's the
        // comparison to make in this situation.
        return (this == obj) || (obj != null && getClass() == obj.getClass()
                                 && hashCode() == obj.hashCode());
    }

    @Override
    public int hashCode() {
        return (room1 != null ? room1.hashCode() : 0)
               + (room2 != null ? room2.hashCode() : 0);
    }
}
