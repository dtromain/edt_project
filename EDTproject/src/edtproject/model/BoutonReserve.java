/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edtproject.model;

import edtproject.CodePrincipal.Cours;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Michaël
 */
public class BoutonReserve 
{
    private Rectangle bouton;
    private Cours cours;

    /**
     *
     * @param bouton
     * @param cours
     */
    public BoutonReserve(Rectangle bouton, Cours cours) {
        this.bouton = bouton;
        this.cours = cours;
    }

    /**
     *
     * @return
     */
    public Rectangle getBouton() {
        return bouton;
    }

    /**
     *
     * @return
     */
    public Cours getCours() {
        return cours;
    }
    
    
}
