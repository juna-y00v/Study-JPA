package hellojpa.dialect;

import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MyH2Dialect {

    public MyH2Dialect() {
        registerFucntion("group_concat",new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }
}
