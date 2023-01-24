package com.mlgmag.sql_db_management;

import com.mlgmag.sql_db_management.operations.Create;
import com.mlgmag.sql_db_management.operations.Delete;
import com.mlgmag.sql_db_management.operations.Read;
import com.mlgmag.sql_db_management.operations.Update;

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
