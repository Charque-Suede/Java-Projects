/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author Salomao Valoi
 */
public class CampoTexto extends JTextField {
    
    private final Color corlinha = new Color(230, 230, 230);
    private Color cor_borda_focos = new Color(96, 176, 255);
    private Color cor_borda_sem_focos = new Color(204, 204, 204);
    private Color cor_texto_fundo = new Color(153, 153, 153);
    protected final JLabel texto_fundo, label;
    private final LineBorder borda_focos = new LineBorder(cor_borda_focos, 2, false);
    private final LineBorder borda_sem_focos = new LineBorder(cor_borda_sem_focos, 2, false);
    
    public CampoTexto() {
        texto_fundo = new JLabel();
        texto_fundo.setFont(new Font("Century Gothic",Font.BOLD,15));
        label = new JLabel();
        this.setOpaque(true);
        this.setBorder(borda_sem_focos);
        this.setFont(this.getFont());
        this.texto_fundo.setHorizontalAlignment(CENTER);
        this.texto_fundo.setForeground(cor_texto_fundo);
        this.setLayout(new BorderLayout());
        this.add(texto_fundo, BorderLayout.CENTER);
        this.setText("");
        addFocusListener(new FocusListener() {
            
            @Override
            public void focusGained(FocusEvent fe) {
                CaixatxtFocusGained(fe);
            }
            
            @Override
            public void focusLost(FocusEvent fe) {
                CaixatxtFocusLost(fe);
            }
        });
    }
    
    public void setBackgroundText() {
        
        if (this.getText().isEmpty()) {
            this.texto_fundo.setVisible(true);
        } else {
            this.texto_fundo.setVisible(false);
        }
    }
    
    private void CaixatxtFocusGained(FocusEvent e) {
        
        setBorder(borda_focos);
        if (this.getText().isEmpty()) {
            this.texto_fundo.setVisible(false);
        }
        
    }
    
    private void CaixatxtFocusLost(FocusEvent e) {
        
        setBorder(borda_sem_focos);
        if (this.getText().isEmpty()) {
            this.texto_fundo.setVisible(true);
        }
    }
    
    public Color getCorTextoFundo() {
        return cor_texto_fundo;
    }
    
    public void setCorTextoFundo(Color cortextofundo) {
        
        this.texto_fundo.setForeground(cortextofundo);
    }
    
    public Color getBorderLineFocusGained() {
        return cor_borda_focos;
    }

    public Color getBorderLineFocusLost() {
        return cor_borda_sem_focos;
    }
    
    public String getTextodoFundo() {
        return texto_fundo.getText();
    }
    
    public void setIconFundo(Icon icon) {
        
        this.label.setIcon(icon);
        
    }
    
    public void setTextoFundo(String emptyText) {
        texto_fundo.setText(emptyText);
    }
    
    public void setFontBackgrounTexto(Font font) {
        this.texto_fundo.setFont(font);
    }
    
}
