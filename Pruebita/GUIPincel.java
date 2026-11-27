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
        
        // Panel de la matriz y su layout

        int filas = matriz.cantFilas();
        int columnas = matriz.cantColumnas();

        
        panelMatriz = new JPanel();
        panelMatriz.setLayout(new GridLayout(filas,columnas));
        panelMatriz.setBorder(BorderFactory.createEmptyBorder(80, 15, 15, 15));
        panelMatriz.setPreferredSize(new Dimension(600, 400));
        

        // Creacion de la matriz

        botonMatriz = new JButton[filas][columnas];
        OyenteMatriz oyenteM = new OyenteMatriz();
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                botonMatriz[i][j] = new JButton();
                botonMatriz[i][j].setBackground(Color.WHITE);
                botonMatriz[i][j].setEnabled(true);
               
               
                

                
                botonMatriz[i][j].addActionListener(oyenteM);

               // Añado
               
                panelMatriz.add(botonMatriz[i][j]);
            }
        }
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
        consultar = new JCheckBox("Ver RGB");
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
            
            // e.getSource() me devuelve el componente que disparó el evento (el botón que se tocó)
            // pero como es un Object genérico. Lo casteo a JButton para poder usarlo como botón
            // getSource() devuelve Object; sé que es un JButton porque solo registré este oyente en la grilla en inicializarPanelMatriz
            
            JButton botonPresionado = (JButton) e.getSource();

            // Leo los tres números del color actual del pincel

            // Después los uso para armar dos objetos distintos:
            //  new Color(r,v,a) hace el Color de Java, para pintar el botón
            //  new ColorRGB(r,v,a) guarda el color para la matriz

            int r = nuevoColor.obtenerRojo();
            int v = nuevoColor.obtenerVerde();
            int a = nuevoColor.obtenerAzul();

            // Pintar el boton (Visualmente funciona)

            botonPresionado.setBackground(new Color(r,v,a));

            // Busco su posicion y guardo en "matriz" para que Ver RGB diga bien el color

            boolean encontre = false;

            for(int i = 0; i < matriz.cantFilas() && !encontre; i++){
                for(int j = 0; j < matriz.cantColumnas() && !encontre; j++){
                    if(botonMatriz[i][j] == botonPresionado){
                        matriz.establecerColor(i, j, new ColorRGB(r, v, a));
                        encontre = true;
                    }
                }
            }

        }
    }
    
    private class OyenteBotonPincel implements ActionListener{
        public void actionPerformed(ActionEvent e){

            //Recupera el texto de los campos de texto de las etiquetas de color

            String textoRojo = rojo.getText();
            String textoVerde = verde.getText(); 
            String textoAzul = azul.getText();

            //Si son digitos, los convierte de String a int, sino envia el mensaje de error

            if(textoRojo.matches("\\d{1,9}") && textoVerde.matches("\\d{1,9}") && textoAzul.matches("\\d{1,9}")){
                int r = Integer.parseInt(textoRojo);
                int v = Integer.parseInt(textoVerde);
                int a = Integer.parseInt(textoAzul);

                //Si los valores ingresados entran en el rango valido, se guarda el color y se actualiza el visor
                //Sino, se envia el mensaje de error

                if(r>=0 && r<=255 && v>=0 && v<=255 && a>=0 && a<=255){
                    nuevoColor = new ColorRGB(r,v,a);
                    panelControlColor.setBackground(new Color(r,v,a));
                } else {
                    mostrarError();
                }
            } else{
                mostrarError();
            }

        }
        
        //Metodo auxiliar que envia el mensaje de error y restaura los valores de los campos de texto
        private void mostrarError(){
            JOptionPane.showMessageDialog(GUIPincel.this, "Ingrese valores válidos", "Error", JOptionPane.ERROR_MESSAGE); 

            rojo.setText(String.valueOf(nuevoColor.obtenerRojo())); 
            verde.setText(String.valueOf(nuevoColor.obtenerVerde())); 
            azul.setText(String.valueOf(nuevoColor.obtenerAzul()));
        }    
    }
    
    private class OyenteBotonCheckBox implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int filas = matriz.cantFilas();
            int columnas = matriz.cantColumnas();

            //Si la casilla es seleccionada, se deshabilitan los botones de la matriz y se muestra el RGB de cada color
            //Sino, se habilitan los botones y se limpia el texto de cada casilla

            if(consultar.isSelected()){
                for(int f = 0; f<filas;f++)
                    for(int c =0; c<columnas;c++){
                        botonMatriz[f][c].setEnabled(false);
                        botonMatriz[f][c].setText(matriz.obtenerColor(f, c).toString());
                }
            }
            else {
                for(int f=0; f<filas;f++)
                    for(int c = 0; c<columnas;c++){
                        botonMatriz[f][c].setEnabled(true);
                        botonMatriz[f][c].setText("");
                }
            }
        }
    } 
}
