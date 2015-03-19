package adapter;

import android.widget.TextView;

/**
 * Created by Daniel on 3/18/2015.
 */
public class DefinitionItemHolder
{
    private TextView definition;
    private TextView partOfSpeech;

    public TextView getDefinition()
    {
        return definition;
    }

    public void setDefinition(TextView definition)
    {
        this.definition = definition;
    }

    public TextView getPartOfSpeech()
    {
        return partOfSpeech;
    }

    public void setPartOfSpeech(TextView partOfSpeech)
    {
        this.partOfSpeech = partOfSpeech;
    }
}
