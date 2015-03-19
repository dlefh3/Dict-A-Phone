package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dlefh3.android.dict_a_phone.R;

/**
 * Created by Daniel on 3/18/2015.
 */
public class CustomArrayAdapter extends ArrayAdapter<DefinitionItemObject>
{
    public CustomArrayAdapter(Context context)
    {
        super(context, 0);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DefinitionItemHolder definitionItemHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
            definitionItemHolder = new DefinitionItemHolder();
            definitionItemHolder.setDefinition((TextView) convertView.findViewById(R.id.definitionTextView));
            definitionItemHolder.setPartOfSpeech((TextView) convertView.findViewById(R.id.partOfSpeachTextView));

            convertView.setTag(definitionItemHolder);
        } else {
            definitionItemHolder = (DefinitionItemHolder) convertView.getTag();
        }
        DefinitionItemObject currentItem = getItem(position);
        definitionItemHolder.getDefinition().setText(currentItem.getDefinition());
        definitionItemHolder.getPartOfSpeech().setText(currentItem.getPartOfSpeech());


        return convertView;
    }
}
