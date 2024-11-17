/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author Salomao Valoi
 */
public class Botao extends JButton {

    private Color cor1 = new Color(163, 205, 170), cor2 = new Color(163, 205, 170);

    public Botao() {
        inicializacao();

    }

    public Botao(String texto) {
        this.setText(texto);
        inicializacao();
    }

    private void inicializacao() {
        setOpaque(true);
        setFocusPainted(false);
        setBorder(new LineBorder(new Color(40, 126, 174), 2));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                MouseEntered(me);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                MouseExisted(me);
            }
        });

    }

    public void setBackgroundMouseEntered(Color cor) {
        this.cor2 = this.getBackground();
        this.cor1 = cor;
    }

    public void setBorder(Color color) {
        setBorder(new LineBorder(color));
    }

    public Color getBackgroundMouseEntered() {
        return cor1;
    }

    private void MouseEntered(MouseEvent x) {
        this.cor2 = this.getBackground();
        this.setBackground(cor1);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBounds(this.getX() - 2, this.getY() - 2, this.getWidth() + 4, this.getHeight() + 4);
    }

    private void MouseExisted(MouseEvent x) {
        this.setBackground(cor2);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.setBounds(this.getX() + 2, this.getY() + 2, this.getWidth() - 4, this.getHeight() - 4);;
    }

}
