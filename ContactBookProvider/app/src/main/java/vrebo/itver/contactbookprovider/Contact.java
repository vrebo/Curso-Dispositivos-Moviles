package vrebo.itver.contactbookprovider;

import android.provider.BaseColumns;

/**
 * Created by CERO on 21/04/2016.
 */
public class Contact implements BaseColumns {

    public static final String TABLE_NAME;
    public static final String COL_NAME;
    public static final String COL_LASTNAME;
    public static final String COL_EMAIL;
    public static final String COL_TELEPHONE;
    public static final String COL_ADDRESS;

    static {
        TABLE_NAME = "CONTACT";
        COL_NAME = "name";
        COL_LASTNAME = "lastName";
        COL_EMAIL = "email";
        COL_TELEPHONE = "telephone";
        COL_ADDRESS = "address";
    }

}
