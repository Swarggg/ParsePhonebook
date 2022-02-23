import java.io.Serializable;

class Worker implements Serializable {

    static int workersCount;

    private String name;
    private String patronymic;
    private String surname;
    private String post;
    private String department;
    private String telephone;

    Worker (String name, String patronymic, String surname, String post, String department, String telephone) {
        this.name=name;
        this.patronymic=patronymic;
        this.surname=surname;
        this.post=post;
        this.department=department;
        this.telephone=telephone;
        workersCount++;
    }

     public String getName() {
         return name;
     }

     public String getPatronymic() {
         return patronymic;
     }

     public String getSurname() {
         return surname;
     }

     public String getPost() {
         return post;
     }

     public String getDepartment() {
         return department;
     }

     public String getTelephone() {
         return telephone;
     }

     @Override
     public String toString () {
        String s= "ФИО: "+this.getSurname()+" "+this.getName()+" "+this.getPatronymic()+"\r\n"+
        "Должность: "+this.getPost()+"\r\n"+
                "Тел: "+this.getTelephone()+"\r\n"+
                "Место: "+this.getDepartment();
        return s;
     }

}
