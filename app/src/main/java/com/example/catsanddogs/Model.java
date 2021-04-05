package com.example.catsanddogs;

import android.content.Context;

import java.util.Random;

public class Model {

    static String CAT = "cat";
    static String DOG = "dog";
    static private String[] mPetList;
    private Context mContext;

    static {
        mPetList = createPetList();
    }

    public Model(Context context){
        mContext = context;
    }

    public MyAdapter populateAdapterData(){
        return new MyAdapter(mContext, mPetList);
    }

    static public String[] createPetList() {
        String[] localPetList = new String[200];
        for(int i=0;i<localPetList.length;i++)
        {
            localPetList[i] = randomFill();
        }
        return localPetList;
    }

    static private String randomFill(){
        Random random = new Random();
        boolean isOne = random.nextBoolean();
        if (isOne) return CAT;
        else return DOG;
    }
}
