package model;
public class User 
{
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    public User(String name,String email,String password,String role)
    {
        this.name=name;
        this.email=email;
        this.password=password;
        this.role=role;
    }   
    public User(String name)
    {
        this.name=name;
    }
    public User(int id, String name, String email, String password, String role) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
}   
    //setter and getter
    public int getid()
    {
        return id;
    }
    public void setid(int id)
    {
        this.id=id;     
    }
    ///
    public String getname()
    {
        return name;
    }
    public void setname(String name)
    {
        this.name=name;
        
    }
    ///
     public String getemail()
    {
        return email;
    }
    public void setemail(String email)
    {
        this.email=email;
        
    }
    //
     public String getpassword()
    {
        return password;
    }
    public void setpassword(String password)
    {
        this.password=password;
        
    }
    //
    public String getrole()
    {
        return role;
    }
    public void setrole(String role)
    {
        this.role=role;  
    } 
}
