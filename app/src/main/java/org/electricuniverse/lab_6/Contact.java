package org.electricuniverse.lab_6;


/**
 * Step 1: Dont forget to add getter and setter methods
 * */
public class Contact
{
    private long id;
    private String name;
    private String lastname;
    private String phone_number;
    public Contact()
    {

    }

    public Contact (String name, String lastname, String phone_number)
    {
        this.name = name;
        this.lastname = lastname;
        this.phone_number = phone_number;
    }

    public String getName()
    {
        return name;
    }

    public String getLastName()
    {
        return lastname;
    }

    public String getPhoneNumber()
    {
        return phone_number;
    }

    public long getID()
    {
        return id;
    }

    public void setName(String newName)
    {
        this.name = newName;
    }

    public void setLastName(String newLastName)
    {
       this.lastname = newLastName;
    }

    public void setPhoneNumber(String newPhoneNumber)
    {
       this.phone_number = newPhoneNumber;
    }

    public void setID(long newID)
    {
        this.id = newID;
    }

}


