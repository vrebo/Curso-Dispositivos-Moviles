package vrebo.itver.contactbook;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by CERO on 25/04/2016.
 */
public class ContactAdapter extends CursorAdapter {

    public ContactAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.contact_list_item, parent, false);
        bindView(v, context, cursor);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view.findViewById(R.id.contact_name)).setText(cursor.getString(cursor.getColumnIndex("name")));
    }
}
