package com.osmar.tcc_mobile;


import junit.framework.TestCase;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class AdicionarComponenteTest extends TestCase {

    private List<Integer> listPinosDesponiveis =new ArrayList<>();



    @Before
    public void setUp(){


        //16 e 2
        listPinosDesponiveis.add(16);
        listPinosDesponiveis.add(2);
        listPinosDesponiveis.add(4);


    }

    @Test
    public void testCriarCompenenteEmUmPinoInvalido(){

        int pinoInvalido=3;
        for(int i=0;i<listPinosDesponiveis.size();i++){
        }


    }

    @After
    public void tearDown(){
        listPinosDesponiveis.clear();
    }
}
