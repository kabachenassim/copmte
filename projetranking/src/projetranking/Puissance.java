package projetranking;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

class Puissance {
    private int n;
    private int m;
    private int[] f;
    private String path = "";

    public Puissance(String path) {
        this.path = path;
        applique();
    }


    

    private void applique() {
    	 LocalDateTime start = LocalDateTime.now();
        List<EltMatrice> result = createTableSortingByJ();
        List<Integer> result2 = createIndexTableByJ(result);
        double[] result3 = vPertinance(result2, result);
        norme(result3);
       
        Duration duration = Duration.between(LocalDateTime.now(), start);
        System.out.println("temps de calcul : "+duration.getSeconds());

    }

    private void norme(double[] array) {
        double somme = 0;
        for (int i = 0; i < n; i++) {
            
            somme += array[i];
        }
        System.out.println("norme de vecteur "+somme);
    }

    private List<EltMatrice> recupereMatrice() {
        File file = new File(path);
        FileInputStream fis;
        int sommetDepart;
        Integer sommetArriver;
        Double proba;
        int degre;
        List<EltMatrice> VectColonne;

        try {
            fis = new FileInputStream(file);

            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            StringTokenizer st = new StringTokenizer(str);
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            f = new int[n];
            for (int i = 0; i < n; i++) {
                f[i] = 1;
            }

            VectColonne = new Vector<EltMatrice>();

            while (st.hasMoreTokens()) {
                double som = 1;
                sommetDepart = Integer.parseInt(st.nextToken());
                degre = Integer.parseInt(st.nextToken());
                if (degre != 0) {
                    f[sommetDepart - 1] = 0;
                }


                for (int i = 0; i < degre; i += 1) {
                    sommetArriver = Integer.parseInt(st.nextToken());
                    proba = Double.parseDouble(st.nextToken());
                    som -= proba;
                    EltMatrice tuple = new EltMatrice(sommetDepart, sommetArriver, proba);
                    VectColonne.add(tuple);
                }
                if (degre != 0 && som != 0) {
                    int s = VectColonne.size();
                    EltMatrice p = VectColonne.get(s - 1);
                    VectColonne.set(s - 1, new EltMatrice(p.i, p.j, p.val + som));

                }
//

            }
            
            return VectColonne;

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


    private List<EltMatrice> createTableSortingByJ() {
        List<EltMatrice> result = recupereMatrice();
        Comparator<? super EltMatrice> comparator = new Comparator<EltMatrice>() {
            @Override
            public int compare(EltMatrice o1, EltMatrice o2) {
                return Integer.compare(o1.j, o2.j);
            }
        };
        result.sort(comparator);
        return result;
    }

    private List<Integer> createIndexTableByJ(List<EltMatrice>matrice) {

        List<Integer> indexTable = new Vector<Integer>();
        indexTable.add(0);
        for (int i = 1; i <matrice.size(); i++) {
            if (matrice.get(i).j !=matrice.get(i - 1).j) {
                indexTable.add(i);
            }
        }
        indexTable.add(matrice.size());
        return indexTable;
    }


    private double deferrance(double[] pi_2, double[] pi_1) {
        double result = 0;
        for (int i = 0; i < n; i++) {
            result += Math.abs(pi_2[i] - pi_1[i]);
        }
        return result;
    }

    private double[] vPertinance(List<Integer> indextable, List<EltMatrice> vectColonne) {
        double segma;
        int nbrIteration = 0;
        double alfa = 0.85;
        double[] pi_2Precedand = new double[n];
        double[] pi_2 = new double[n];
        int index;
        double initialValue = 1 / (double) n;
        for (int i = 0; i < n; i++) {
            //L'initialisation du premier vecteur par la valeur initialValue qui egale à 1/n
            pi_2[i] = initialValue;
        }
        //le calcule de la différence entre les vecteurs de l'iteration courante et précedente
        double DeferancePkAndPkPrecedente = deferrance(pi_2, pi_2Precedand);
        while (DeferancePkAndPkPrecedente > 0.000000001) {
            nbrIteration++;
            segma = 0;
            for (int i = 0; i < n; i++) {
                pi_2Precedand[i] = pi_2[i];
                segma += pi_2[i] * f[i];
            }

            for (int i = 0; i < n; i++) {
                pi_2[i] = (1 - alfa + segma * alfa) / n;
            }

            for (int i = 0; i < indextable.size() - 1; i++) {
                int start = indextable.get(i);
                int end = indextable.get(i + 1);
                index = vectColonne.get(start).j - 1;
                pi_2[index] = 0;
                for (int j = start; j < end; j++) {
                    EltMatrice EltMatrice = vectColonne.get(j);
                    pi_2[index] += pi_2Precedand[EltMatrice.i - 1] * EltMatrice.val;
                }

                pi_2[index] = alfa * pi_2[index] + (1 - alfa + segma * alfa) / n;

            }
            //le calcule de la différence entre les vecteurs de l'iteration courante et précedente
            DeferancePkAndPkPrecedente = deferrance(pi_2, pi_2Precedand);


        }
        System.out.println(DeferancePkAndPkPrecedente);
        System.out.println("nbrIetration " + nbrIteration);
        return pi_2;
    }

}


