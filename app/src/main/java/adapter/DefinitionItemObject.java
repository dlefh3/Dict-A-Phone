package adapter;

/**
 * Created by Daniel on 3/18/2015.
 */
public class DefinitionItemObject
{
    String definition;
    String partOfSpeech;

    public DefinitionItemObject(String def, String pof)
    {
        this.definition = def;
        this.partOfSpeech = pof;
    }

    public String getDefinition()
    {
        return definition;
    }

    public void setDefinition(String definition)
    {
        this.definition = definition;
    }

    public String getPartOfSpeech()
    {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech)
    {
        this.partOfSpeech = partOfSpeech;
    }
}
