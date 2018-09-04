package rm.repo;

import io.realm.Realm;
import rm.Dog;

public class DogRepositoryImpl implements DogRepository {
    @Override
    public void createDog(final String name) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            Dog dog = r.createObject(Dog.class);
            dog.setName(name);
        });
        realm.close();
    }
}
