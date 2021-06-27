public class UsersArrayList implements UsersList{
    private User[] mass;
    private Integer capacity;
    private Integer len;

    public UsersArrayList() {
        this.mass = new User[10];
        this.len = 0;
        this.capacity = 10;
    }

    public void addUser(User user) {
        if (capacity <= len)
        {
            User[] newmass = new User[capacity * 2];
            int i;
            for (i = 0; i < len; i++)
            {
                newmass[i] = mass[i];
            }
            newmass[i] = user;
            len++;
            capacity *= 2;
            mass = newmass;
        }
        else {
            mass[len] = user;
            len++;
        }
    }

    public User getUserByID(int id) throws UserNotFoundException {
        int i;
        for (i = 0; i < len; i++)
        {
            if (mass[i].getId() == id)
                return mass[i];
        }
        throw new UserNotFoundException(id);
    }

    public User getUserByIndex(int index) throws UserNotFoundException {
        if (index >= len)
            throw new UserNotFoundException(index);
        return mass[index];
    }

    public Integer NumberOfUsers() {
        return len;
    }
}

class UserNotFoundException extends RuntimeException{
    private int id;
    public UserNotFoundException(int i)
    {
        this.id = i;
    }
    public void printStackTrace(){
        System.err.println("UserNotFoundException - id = "+ id);
    }
}
