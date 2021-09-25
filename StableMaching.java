import java.util.HashMap;

public class StableMaching {
    private String[] mens;
    private String[] womens;

    private int[][] preferenceMens;
    private int[][] preferenceWomens;

    // Women
    private int[] womanPair;
    private boolean[] womanPaired;

    // Men
    private int[] menPair;
    private boolean[] menPaired;
    private int[] inviteWoman;

    private boolean flag;
    private int hTem;

    public StableMaching(String[] men, String[] women, int[][] preferenceMen, int[][] preferenceWomen) {
        mens = men;
        womens = women;
        preferenceMens = preferenceMen;
        preferenceWomens = preferenceWomen;

        womanPair = new int[women.length];
        womanPaired = new boolean[women.length];

        menPair = new int[men.length];
        menPaired = new boolean[men.length];
        inviteWoman = new int[women.length];
    }

    public HashMap<String, String> match() {

        for (int h = 0; h < mens.length; h++) {

            // validar si esta soltero
            if (singleMen(h)) {
                int m = optionInvite(h);

                // int h = getSingleMen();

                if (singleWoman(m)) {
                    addMatch(h, m);
                    // Temporal
                    if (flag) {
                        h = hTem;
                        flag = false;
                    }
                } else if (preferChange(h, m)) {

                    // Temporal
                    h = pair(m) - 1; // OJO puede que no sea (-1)
                    hTem = h; // puede ser +1
                    flag = true;

                    unmatch(h+1, m); // solo al hombre
                    addMatch(h+1, m);
                } else {
                    rejected(h, m);
                    h--; // continuamos con el mismo hombre
                }
            }
        }

        return null;
    }

    private int pair(int m) {
        return womanPair[m];
    }

    private void rejected(int h, int m) {

    }

    private void unmatch(int h, int m) {
        menPair[h] = -1;
        menPaired[h] = false;
    }

    private boolean preferChange(int h1, int m) {
        int h = pair(m);
        for (int i = 0; i < preferenceWomens[m].length; i++) {
            if (preferenceMens[m][i] == h1) {
                return true;
            }

            if (preferenceMens[m][i] == h) {
                return false;
            }
        }

        return false;
    }

    private void addMatch(int h, int m) {
        menPair[h] = m;
        menPaired[h] = true;
        womanPair[m] = h;
        womanPaired[m] = true;
    }

    private boolean singleMen(int m) {

        return !menPaired[m];
    }

    private boolean singleWoman(int w) {
        return !womanPaired[w];
    }

    private int optionInvite(int h) {
        // int tem = inviteWoman[h];
        // if (tem == womens.length - 1) {
        //     return -1;
        // } else {
        //     inviteWoman[h] = tem + 1;
        //     return tem + 1;
        // }

        return inviteWoman[h]++;
    }

   

    // public void test() {
    //     for (int[] bs : inviteWoman) {
    //         System.out.println(bs[0]);
    //     }
    // }

}
