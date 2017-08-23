import CRUD_Operations.Create;
import CRUD_Operations.Delete;
import CRUD_Operations.Read;
import CRUD_Operations.Update;

/**
 *
 * Created by Mag on 23.08.2017.
 *
 */
class CRUD_DAO {

    void delete() {
        new Delete();
    }

    void create(){
        new Create();
    }

    void read(){
        new Read();
    }

    void update(){
        new Update();
    }
}
