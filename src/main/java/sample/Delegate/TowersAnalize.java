package sample.Delegate;

public class TowersAnalize implements IAnalizeText {

    String[] text;

    TowersAnalize(String[] text){
        this.text = text;
    }

    @Override
    public int Analize() {
        for(int i = 0; i < text.length; i++){
            char[] str = text[i].toCharArray();
            String lex = "";
            for(int j = 0; j < str.length; j++){
                if(str[j] == ' ')
                    continue;
                lex += str[j];
                if(j == 3) {
                    if (!getLexMove(lex))
                        return i;
                    else
                        lex = "";
                }
                if(j == 4) {
                    if (!getLeftSk(lex))
                        return i;
                    else
                        lex = "";
                }
                if(j == 5){
                    if(!getNumb(lex)) {
                        return i;
                    }
                    else
                        lex = "";
                }
                if(j == 6) {
                    if(!getZpt(lex))
                        return i;
                    else
                        lex = "";
                }
                if(j == 7) {
                    if(!getNumb(lex))
                        return i;
                    else
                        lex = "";
                }
                if(j == 8) {
                    if(!getRightSk(lex))
                        return i;
                    else
                        lex = "";
                }
            }
        }
        return -1;
    }

    @Override
    public int[] getNumbers() {
        return new int[0];
    }

    private boolean getLexMove(String string){
        if(string.equals("Move"))
            return true;
        return false;
    }

    private boolean getLeftSk(String string){
        if(string.equals("(")){
            return true;
        }
        return false;
    }

    private boolean getNumb(String string){
        try{
            Integer.parseInt(string);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    private boolean getZpt(String string){
        if(string.equals(","))
            return true;
        return false;
    }

    private boolean getRightSk(String string){
        if(string.equals(")"))
            return true;
        return false;
    }

}