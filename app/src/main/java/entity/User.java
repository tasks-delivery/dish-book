package entity;

import io.realm.RealmList;
import io.realm.RealmObject;

public class User extends RealmObject {

    private String name;
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
    return name;
  }

    public void setWorks(RealmList<Work> works) {
    this.works = works;
  }

    public RealmList<Work> getWorks() {
    return works;
  }

    @Override
    public String toString() {
        return name;
    }

    // public String getId() {
 //       return id;
  //  }

  //  public void setId(String id) {
 //       this.id = id;
 //   }
}
