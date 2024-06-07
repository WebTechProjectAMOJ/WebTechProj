package models.user;

public interface login {
    public boolean verify(String password);

    public String getAccountType();

    public boolean equals(Object O);
}
