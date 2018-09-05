package entity;

import io.realm.RealmList;
import io.realm.RealmObject;

public class User extends RealmObject {

    private String name;
<<<<<<< HEAD
=======

    private String description;

>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
    private RealmList<Work> works;

    private Work work;

    public void setWork(Work work) {
        this.work = work;
    }

    public Work getWork() {
        return work;
    }

    public void setName(String name) {
    this.name = name;
  }

    public String getName() {
<<<<<<< HEAD
    return name;
  }

    public void setWorks(RealmList<Work> works) {
    this.works = works;
  }
=======
      return name;
    }

    public void setWorks(RealmList<Work> works) {
      this.works = works;
    }
>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330

    public RealmList<Work> getWorks() {
    return works;
  }

<<<<<<< HEAD
    @Override
    public String toString() {
        return name;
=======
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
    }

    // public String getId() {
 //       return id;
  //  }

  //  public void setId(String id) {
 //       this.id = id;
 //   }
}
