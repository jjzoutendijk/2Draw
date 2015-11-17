
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
package _2Draw.Panels;
 
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Server.Player;
import Server.Shape;
import _2Draw.Game.Game;
 
/* ColorChooserDemo.java requires no other files. */
public class ColorChooser extends JPanel implements ChangeListener, ActionListener {
	/* ------------------------------------------------------------------------------------------------------
	 * Class Variables
	 * ------------------------------------------------------------------------------------------------------
	 */
    protected JColorChooser tcc;
    protected JLabel banner;
    private JPanel rightPanel;
    private ToolPanel leftPanel;
    private JButton colorConfirmed;
    private CanvasPanel canvasPanel;
 
	/* ------------------------------------------------------------------------------------------------------
	 * Constructor
	 * ------------------------------------------------------------------------------------------------------
	 */
    public ColorChooser(ToolPanel leftPanel, JPanel rightPanel, CanvasPanel canvasPanel, Player p1) {
        super(new BorderLayout());
        
        this.leftPanel = leftPanel;
        this.rightPanel = rightPanel;
        this.canvasPanel = canvasPanel;
        this.leftPanel.addConfirmationListener(this);
 
        //Set up the banner at the top of the window
        banner = new JLabel("Please choose a color:",JLabel.CENTER);
        colorConfirmed = new JButton("Confirm");
        banner.setForeground(Color.black);
        banner.setBackground(Color.white);
        banner.setOpaque(true);
        banner.setFont(new Font("SansSerif", Font.BOLD, 24));
        banner.setPreferredSize(new Dimension(100, 65));
 
        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.add(banner, BorderLayout.CENTER);
        bannerPanel.add(colorConfirmed, BorderLayout.EAST);
        bannerPanel.setBorder(BorderFactory.createTitledBorder("Banner"));
 
        //Set up color chooser for setting text color
        tcc = new JColorChooser(banner.getForeground());
        tcc.getSelectionModel().addChangeListener(this);
        tcc.setBorder(BorderFactory.createTitledBorder("Choose Text Color"));
 
        add(bannerPanel, BorderLayout.CENTER);
        add(tcc, BorderLayout.PAGE_END);
        
		colorConfirmed.addActionListener(this);
    }
    
	/* ------------------------------------------------------------------------------------------------------
	 * Class Methods
	 * ------------------------------------------------------------------------------------------------------
	 */
    @Override
    /**
     * Action performed method, switched panels when the confirm button is clicked
     */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.colorConfirmed)){
			CardLayout cl = (CardLayout)(rightPanel.getLayout());
			cl.show(rightPanel, Game.CANVAS);
		}
		if (canvasPanel.getActiveShape() != null){ 
			Shape activeShape = canvasPanel.getActiveShape();
			activeShape.setColor(banner.getForeground());
		}
		
	}
    
    /**
     * Method called when a different color is chosen.
     */
    public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();
        banner.setForeground(newColor);
    }


	

}