package ro.sci.gr14.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class DBEntity {
    private Long id;
    private String name;

    public DBEntity(){
        this.id=1L;
        this.name="";
    }

    public DBEntity(Long id, String name){
        this.id=id;
        this.name=name;
    }
}
