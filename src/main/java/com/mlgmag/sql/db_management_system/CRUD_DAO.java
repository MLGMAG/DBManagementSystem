package com.mlgmag.sql.db_management_system;

import com.mlgmag.sql.db_management_system.operations.Create;
import com.mlgmag.sql.db_management_system.operations.Delete;
import com.mlgmag.sql.db_management_system.operations.Read;
import com.mlgmag.sql.db_management_system.operations.Update;

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
