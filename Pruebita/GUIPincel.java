package Pruebita;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GUIPincel extends JFrame{
    
    //Atributos de la aplicación
    private MatrizColor matriz;
    private ColorRGB nuevoColor;
    
    //Objetos Gráficos
    //BOTONES
    private JButton boton;   
    private JButton [][] botonMatriz;
    private JCheckBox  consultar;
    
    //ETIQUETAS
    private JLabel etiquetaRojo;
    private JLabel etiquetaVerde;
    private JLabel etiquetaAzul; 
    
    //CAMPOS DE TEXTO
    private JTextField rojo;
    private JTextField verde;
    private JTextField azul;
    
    //PANELES
    private JPanel panelMenu;
    private JPanel panelMatriz;   
    private JPanel panelContainerControlColor;
    private JPanel panelContainerBoton;
    private JPanel panelColores;
    private JPanel panelControlColor;
    
    public GUIPincel(MatrizColor c){
        super("Pincel de IPOO");
        
        matriz=c;
        nuevoColor= new ColorRGB(0,0,0);

        setSize(700, 600);
        
        getContentPane().setLayout(new BorderLayout()); 
        
        inicializarPanelMenu();
        inicializarPanelMatriz();

        //Agrego cada panel al panel de contenido
        getContentPane().add(panelMatriz, BorderLayout.NORTH);
        getContentPane().add(panelMenu, BorderLayout.SOUTH);
        
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void inicializarPanelMatriz(){

    }

    // Construye e inicializa el menú inferior dividiéndolo en 3 subpaneles:
    
    private void inicializarPanelMenu(){
        
        //1. Panel contenedor del menú

        panelMenu = new JPanel();
        panelMenu.setLayout(new BorderLayout());

        //2. Entradas númericas para componentes RGB (OESTE)

        panelColores = new JPanel();
        panelColores.setLayout(new GridLayout(3,2,5,5));

        etiquetaRojo = new JLabel("Rojo:");
        rojo = new JTextField("0");
        etiquetaVerde = new JLabel("Verde:");
        verde = new JTextField("0");
        etiquetaAzul = new JLabel("Azul:");
        azul = new JTextField("0");

        panelColores.add(etiquetaRojo);
        panelColores.add(rojo);
        panelColores.add(etiquetaVerde);
        panelColores.add(verde);
        panelColores.add(etiquetaAzul);
        panelColores.add(azul);

        //3. Visor gráfico del color activo del pincel (CENTRO)

        panelContainerControlColor = new JPanel();
        panelControlColor = new JPanel();
        panelControlColor.setPreferredSize(new Dimension(35,35));
        panelControlColor.setBackground(Color.BLACK);

        panelContainerControlColor.add(panelControlColor);
        
        //4. Componentes reactivas y registración de oyentes (SUR)

        panelContainerBoton = new JPanel();
        boton = new JButton("Confirmar Color");
        boton.addActionListener(new OyenteBotonPincel());
        consultar = new JCheckBoz("Ver RGB");
        consultar.addActionListener(new OyenteBotonCheckBox());

        panelContainerBoton.add(boton);
        panelContainerBoton.add(consultar);

        //5. Se insertan los subpaneles en el contenedor principal
        
        panelMenu.add(panelColores, BorderLayout.WEST);
        panelMenu.add(panelContainerControlColor, BorderLayout.CENTER);
        panelMenu.add(panelContainerBoton, BorderLayout.SOUTH);
    }
    
    // Prueba
    
    private class OyenteMatriz implements ActionListener{
        public void actionPerformed(ActionEvent e){

        }
    }
    
    private class OyenteBotonPincel implements ActionListener{
        public void actionPerformed(ActionEvent e){

        }
    }
    
    private class OyenteBotonCheckBox implements ActionListener{
        public void actionPerformed(ActionEvent e){
            
        }
    } 
}
