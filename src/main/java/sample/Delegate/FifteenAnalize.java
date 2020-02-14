package sample.Delegate;

public class FifteenAnalize implements IAnalizeText {

    String[] text;
    int[] Numbers;

    FifteenAnalize(String[] text){
        this.text = text;
    }

    @Override
    public int Analize() {
        this.Numbers = new int[text.length];
        for(int i = 0; i < text.length; i++){
            char[] str = text[i].toCharArray();
            int nk; // Под разряд числа, так как оно может быть двузначным и трёхзначным и т.д.;
            String lex = "";
            nk = 0;
            int numLex = 0; //Порядок лексемы
            for(int j = 0; j < str.length; j++){
                if(str[j] == ' ')
                    continue;
                lex += str[j];
                if(lex.length() == 4 && numLex == 0) {
                    if (!getLexMove(lex)) {
                        return i;
                    }
                    else {
                        lex = "";
                        numLex = 1;
                        continue;
                    }
                }
                if(lex.length() == 1 && numLex == 1) {
                    if (!getLeftSk(lex))
                        return i;
                    else {
                        lex = "";
                        numLex = 2;
                        continue;
                    }
                }
                if(numLex == 2){
                    while(getNumb(lex)){
                        j++;
                        nk++;
                        lex += str[j];
                    }
                    if(nk == 0){
                        return i;
                    }
                    else {
                        numLex = 3;
                        this.Numbers[i] = Integer.parseInt(lex.replace(String.valueOf(str[j]),""));
                        j--;
                        lex = "";
                        continue;
                    }
                }
                if(lex.length() == 1 && numLex == 3) {
                    if(!getRightSk(lex))
                        return i;
                    else {
                        lex = "";
                        numLex = 0;
                        continue;
                    }
                }
            }
        }
        return -1;
    }

    private boolean getLexMove(String string){
        if(string.equals("Move"))
            return true;
        return false;
    }

    public int[] getNumbers(){
        return this.Numbers;
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

    private boolean getRightSk(String string){
        if(string.equals(")"))
            return true;
        return false;
    }
}
