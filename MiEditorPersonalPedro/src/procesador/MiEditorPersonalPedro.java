package procesador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;

public class MiEditorPersonalPedro {

	public static void main(String[] args) {
		Ventana ventana = new Ventana();
	}
}

class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;

	public Ventana() {
		
		setSize(1200, 800);
		setTitle("Mi editor personal");
		setIconImage(new ImageIcon(getClass().getResource("/resources/icon.png")).getImage());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		Lamina lamina = new Lamina();
		add(lamina);
		
		setVisible(true);
	}
}	

class Lamina extends JPanel {
	
	private static final long serialVersionUID = 1L;

	// Creo la barra con sus menús
	JMenuBar barra = new JMenuBar();
	
	JMenu mArchivo = new JMenu(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menu_archivo"));
	JMenu mFuente = new JMenu(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menu_fuente"));
	JMenu mEstilo = new JMenu(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menu_estilo"));
	JMenu mParrafo = new JMenu(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menu_parrafo"));
	JMenu mTamanio = new JMenu(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menu_tamanio"));
	JMenu mColor = new JMenu(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menu_color"));
	JMenu mInformacion = new JMenu(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menu_informacion"));
	JMenu mIdioma = new JMenu(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menu_idioma"));
	JPopupMenu mContextual = new JPopupMenu();
	
	// Añado los items de cada menú a su menú
	JMenuItem miAbrir = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_abrir"));
	JMenuItem miGuardar = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_guardar"));
	JMenuItem miImagen = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_imagen"));
	JMenuItem miSalir = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_salir"));
	
	JMenuItem miArial = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_arial"));
	JMenuItem miVerdana = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_verdana"));
	JMenuItem miCourier = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_courier"));
	JMenuItem miImpact = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_impact"));
	
	JMenuItem popNegrita, popCursiva, popSubrayado;
	JMenuItem popIzquierda, popCentrado, popDerecha, popJustificado;
	JMenuItem popCortar, popCopiar, popPegar;
	
	JMenuItem miNegrita = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_negrita"));
	JMenuItem miCursiva = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_cursiva"));
	JMenuItem miSubrayado = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_subrayado"));
	
	JMenuItem miIzquierda = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_izquierda"));
	JMenuItem miCentrado = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_centrado"));
	JMenuItem miDerecha = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_derecha"));
	JMenuItem miJustificado = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_justificado"));
	
	JMenuItem miPt10 = new JMenuItem("10 pt");
	JMenuItem miPt14 = new JMenuItem("14 pt");
	JMenuItem miPt18 = new JMenuItem("18 pt");
	JMenuItem miPt22 = new JMenuItem("22 pt");
	
	JMenuItem miColorFuente = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_color"));
	
	JMenuItem miAcerca = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_acerca"));
	
	JMenuItem miEspaniol = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_espaniol"));
	JMenuItem miIngles = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_menuItem_england"));
	
	//Creo la barra de herramientas con todos sus elementos
	JToolBar toolbarra = new JToolBar();
	
	JButton btnAbrir = new JButton();
	JButton btnGuardar = new JButton();
	JButton btnImagen = new JButton();
	JButton btnSalir = new JButton();
	
	JLabel lbFuente = new JLabel(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_label_fuente"));
	JComboBox<String> comboFuente = new JComboBox<String>();
	
	JLabel lbTamanio  = new JLabel(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_label_tamanio"));
	JComboBox<Integer> comboSize = new JComboBox<Integer>();
	
	JButton btnNegrita = new JButton();
	JButton btnCursiva = new JButton();
	JButton btnSubrayado = new JButton();
	
	JButton btnIzquierda = new JButton();
	JButton btnCentrado = new JButton();
	JButton btnDerecha = new JButton();
	JButton btnJustificado = new JButton();
	
	JButton btnColor = new JButton();
	
	JButton btnSpain = new JButton();
	JButton btnEngland = new JButton();
	
	JLabel lbSkin = new JLabel(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_label_skin"));
	JComboBox<String> comboSkin = new JComboBox<String>();
	
	//Creación text pane y scroll pane para el texto
	JTextPane area = new JTextPane();
	JScrollPane sp1 = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	//Cojo todas las fuentes del ordenador
	String[] fuentes = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

	//Constructor
	public Lamina() {
	
		//El layout será null, por lo que tenemos que ubicar cada elemento, uno a uno
		setLayout(null);
		
		//Añado los menús a la barra
		barra.add(mArchivo);
		barra.add(mFuente);
		barra.add(mEstilo);
		barra.add(mParrafo);
		barra.add(mTamanio);
		barra.add(mColor);
		barra.add(mInformacion);
		barra.add(mIdioma);
		barra.setBounds(0, 1, 1195, 30);
		add(barra);
		
		//Añado los menuItem a cada menú
		miAbrir.setIcon(new ImageIcon(getClass().getResource("/resources/open.png")));
		miAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		miAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser explorador = new JFileChooser();
				if (explorador.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					String ruta = explorador.getSelectedFile().getPath();
					try {
						BufferedReader reader = new BufferedReader(new FileReader(ruta));
						String textoTotal="";
						String linea = reader.readLine();
						while (linea != null) {
						textoTotal = textoTotal + linea + System.getProperty("line.separator");
						linea = reader.readLine();
						}
						area.setText(textoTotal);
						reader.close();
					}catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		});
		mArchivo.add(miAbrir);
		miGuardar.setIcon(new ImageIcon(getClass().getResource("/resources/save.png")));
		miGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		miGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser explorador = new JFileChooser();
				if (JFileChooser.APPROVE_OPTION == explorador.showSaveDialog(null)) {
					String ruta = explorador.getSelectedFile().getPath();
					try {
						PrintWriter writer = new PrintWriter(ruta);
						writer.print(area.getText());
						writer.close();
					}catch (Exception e2) {
						System.out.println(e2.getMessage());
					}
				}
			}
		});
		mArchivo.add(miGuardar);
		miImagen.setIcon(new ImageIcon(getClass().getResource("/resources/image.png")));
		miImagen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
		        FileNameExtensionFilter picture = new FileNameExtensionFilter("JPEG files (*.png)", "png");
		        fc.setFileFilter(picture);
		        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		        if (JFileChooser.APPROVE_OPTION == fc.showSaveDialog(null)) {
			        String filename = fc.getSelectedFile().getAbsolutePath();
	
			        // Si no entra texto se refresca el dialogbox
			        if (filename == null) return;
	
			        try {
			            BufferedImage img = ImageIO.read(new File(filename));
			            ImageIcon pictureImage = new ImageIcon(img);
			            area.insertIcon(pictureImage);
			        } 
	
			        catch (IOException e3) {
			            JOptionPane.showMessageDialog(null, "Could not find file: " + filename);
			        }
		        }
			}    
		});
		mArchivo.add(miImagen);
		miSalir.setIcon(new ImageIcon(getClass().getResource("/resources/exit.png")));
		miSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String salir = ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_string_salir");
				int input = JOptionPane.showConfirmDialog(null, salir);
				if (input == 0) {
					System.exit(0);
				}
			}
		});
		mArchivo.add(miSalir);
		
		miArial.addActionListener(new StyledEditorKit.FontFamilyAction("Fuente", "Arial"));
		mFuente.add(miArial);
		miVerdana.addActionListener(new StyledEditorKit.FontFamilyAction("Fuente", "Verdana"));
		mFuente.add(miVerdana);
		miCourier.addActionListener(new StyledEditorKit.FontFamilyAction("Fuente", "Courier"));
		mFuente.add(miCourier);
		miImpact.addActionListener(new StyledEditorKit.FontFamilyAction("Fuente", "Impact"));
		mFuente.add(miImpact);
		
		miNegrita.setIcon(new ImageIcon(getClass().getResource("/resources/negrita.png")));
		miNegrita.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		miNegrita.addActionListener(new StyledEditorKit.BoldAction());
		mEstilo.add(miNegrita);
		miCursiva.setIcon(new ImageIcon(getClass().getResource("/resources/cursiva.png")));
		miCursiva.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK));
		miCursiva.addActionListener(new StyledEditorKit.ItalicAction());
		mEstilo.add(miCursiva);
		miSubrayado.setIcon(new ImageIcon(getClass().getResource("/resources/subrayado.png")));
		miSubrayado.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		miSubrayado.addActionListener(new StyledEditorKit.UnderlineAction());
		mEstilo.add(miSubrayado);
		
		miIzquierda.setIcon(new ImageIcon(getClass().getResource("/resources/izquierda.png")));
		miIzquierda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
		miIzquierda.addActionListener(new StyledEditorKit.AlignmentAction("Izquierda", 3));
		mParrafo.add(miIzquierda);
		miCentrado.setIcon(new ImageIcon(getClass().getResource("/resources/centrado.png")));
		miCentrado.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
		miCentrado.addActionListener(new StyledEditorKit.AlignmentAction("Centrado", 1));
		mParrafo.add(miCentrado);
		miDerecha.setIcon(new ImageIcon(getClass().getResource("/resources/derecha.png")));
		miDerecha.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
		miDerecha.addActionListener(new StyledEditorKit.AlignmentAction("Derecha", 2));
		mParrafo.add(miDerecha);
		miJustificado.setIcon(new ImageIcon(getClass().getResource("/resources/justificado.png")));
		miJustificado.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.CTRL_DOWN_MASK));
		miJustificado.addActionListener(new StyledEditorKit.AlignmentAction("Justificado", 4));
		mParrafo.add(miJustificado);
		
		miPt10.addActionListener(new StyledEditorKit.FontSizeAction("Tamaño", 10));
		mTamanio.add(miPt10);
		miPt14.addActionListener(new StyledEditorKit.FontSizeAction("Tamaño", 14));
		mTamanio.add(miPt14);
		miPt18.addActionListener(new StyledEditorKit.FontSizeAction("Tamaño", 18));
		mTamanio.add(miPt18);
		miPt22.addActionListener(new StyledEditorKit.FontSizeAction("Tamaño", 22));
		mTamanio.add(miPt22);
		
		miColorFuente.setIcon(new ImageIcon(getClass().getResource("/resources/color.png")));
	    miColorFuente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JColorChooser vColores = new JColorChooser();
				String mensaje = ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_string_color");
				Color color = vColores.showDialog(null, mensaje, Color.gray);
				
				int start = area.getSelectionStart();
				int end = area.getSelectionEnd();
				int selectedLength = end - start;
				
			    StyledDocument style = area.getStyledDocument();
			    AttributeSet oldSet = style.getCharacterElement(end-1).getAttributes();
			    StyleContext sc = StyleContext.getDefaultStyleContext();
			    AttributeSet s = sc.addAttribute(oldSet, StyleConstants.Foreground, Color.RED);
			    style.setCharacterAttributes(start, selectedLength, s, true);
			}
		});
		mColor.add(miColorFuente);
		
		miAcerca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String mensaje = ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_string_mensaje");
				String cabecera = ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_string_acerca");
				JOptionPane.showMessageDialog(null, mensaje, cabecera, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mInformacion.add(miAcerca);
		
		miEspaniol.setIcon(new ImageIcon(getClass().getResource("/resources/spain.png")));
		miEspaniol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				ResourceBundle r = ResourceBundle.getBundle("etiquetas.Etiquetas");
				mArchivo.setText(r.getString("etiqueta_menu_archivo"));
				mFuente.setText(r.getString("etiqueta_menu_fuente"));
				mEstilo.setText(r.getString("etiqueta_menu_estilo"));
				mParrafo.setText(r.getString("etiqueta_menu_parrafo"));
				mTamanio.setText(r.getString("etiqueta_menu_tamanio"));
				mColor.setText(r.getString("etiqueta_menu_color"));
				mInformacion.setText(r.getString("etiqueta_menu_informacion"));
				mIdioma.setText(r.getString("etiqueta_menu_idioma"));
				miAbrir.setText(r.getString("etiqueta_menuItem_abrir"));
				miGuardar.setText(r.getString("etiqueta_menuItem_guardar"));
				miImagen.setText(r.getString("etiqueta_menuItem_imagen"));
				miSalir.setText(r.getString("etiqueta_menuItem_salir"));
				miNegrita.setText(r.getString("etiqueta_menuItem_negrita"));
				miCursiva.setText(r.getString("etiqueta_menuItem_cursiva"));
				miSubrayado.setText(r.getString("etiqueta_menuItem_subrayado"));
				miIzquierda.setText(r.getString("etiqueta_menuItem_izquierda"));
				miCentrado.setText(r.getString("etiqueta_menuItem_centrado"));
				miDerecha.setText(r.getString("etiqueta_menuItem_derecha"));
				miJustificado.setText(r.getString("etiqueta_menuItem_justificado"));
				miColorFuente.setText(r.getString("etiqueta_menuItem_color"));
				miAcerca.setText(r.getString("etiqueta_menuItem_acerca"));
				miEspaniol.setText(r.getString("etiqueta_menuItem_espaniol"));
				miIngles.setText(r.getString("etiqueta_menuItem_england"));
				btnAbrir.setToolTipText(r.getString("etiqueta_tooltip_abrir"));
				btnGuardar.setToolTipText(r.getString("etiqueta_tooltip_guardar"));
				btnSalir.setToolTipText(r.getString("etiqueta_tooltip_salir"));
				lbFuente.setText(r.getString("etiqueta_label_fuente"));
				comboFuente.setToolTipText(r.getString("etiqueta_tooltip_fuente"));
				lbTamanio.setText(r.getString("etiqueta_label_tamanio"));
				comboSize.setToolTipText(r.getString("etiqueta_tooltip_tamanio"));
				btnNegrita.setToolTipText(r.getString("etiqueta_tooltip_negrita"));
				btnCursiva.setToolTipText(r.getString("etiqueta_tooltip_cursiva"));
				btnSubrayado.setToolTipText(r.getString("etiqueta_tooltip_subrayado"));
				btnIzquierda.setToolTipText(r.getString("etiqueta_tooltip_izquierda"));
				btnCentrado.setToolTipText(r.getString("etiqueta_tooltip_centrado"));
				btnDerecha.setToolTipText(r.getString("etiqueta_tooltip_derecha"));
				btnJustificado.setToolTipText(r.getString("etiqueta_tooltip_justificado"));
				btnColor.setToolTipText(r.getString("etiqueta_tooltip_color"));
				btnSpain.setToolTipText(r.getString("etiqueta_tooltip_espaniol"));
				btnEngland.setToolTipText(r.getString("etiqueta_tooltip_england"));
				lbSkin.setText(r.getString("etiqueta_label_skin"));
				comboSkin.setToolTipText(r.getString("etiqueta_tooltip_skin"));
				popCortar.setText(r.getString("etiqueta_pop_cortar"));
				popCopiar.setText(r.getString("etiqueta_pop_copiar"));
				popPegar.setText(r.getString("etiqueta_pop_pegar"));
				popNegrita.setText(r.getString("etiqueta_pop_negrita"));
				popCursiva.setText(r.getString("etiqueta_pop_cursiva"));
				popSubrayado.setText(r.getString("etiqueta_pop_subrayado"));
				popIzquierda.setText(r.getString("etiqueta_pop_izquierda"));
				popCentrado.setText(r.getString("etiqueta_pop_centrado"));
				popDerecha.setText(r.getString("etiqueta_pop_derecha"));
				popJustificado.setText(r.getString("etiqueta_pop_justificado"));
			}
		});
		mIdioma.add(miEspaniol);
		miIngles.setIcon(new ImageIcon(getClass().getResource("/resources/england.png")));
		miIngles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en"));
				ResourceBundle r = ResourceBundle.getBundle("etiquetas.Etiquetas");
				mArchivo.setText(r.getString("etiqueta_menu_archivo"));
				mFuente.setText(r.getString("etiqueta_menu_fuente"));
				mEstilo.setText(r.getString("etiqueta_menu_estilo"));
				mParrafo.setText(r.getString("etiqueta_menu_parrafo"));
				mTamanio.setText(r.getString("etiqueta_menu_tamanio"));
				mColor.setText(r.getString("etiqueta_menu_color"));
				mInformacion.setText(r.getString("etiqueta_menu_informacion"));
				mIdioma.setText(r.getString("etiqueta_menu_idioma"));
				miAbrir.setText(r.getString("etiqueta_menuItem_abrir"));
				miGuardar.setText(r.getString("etiqueta_menuItem_guardar"));
				miImagen.setText(r.getString("etiqueta_menuItem_imagen"));
				miSalir.setText(r.getString("etiqueta_menuItem_salir"));
				miNegrita.setText(r.getString("etiqueta_menuItem_negrita"));
				miCursiva.setText(r.getString("etiqueta_menuItem_cursiva"));
				miSubrayado.setText(r.getString("etiqueta_menuItem_subrayado"));
				miIzquierda.setText(r.getString("etiqueta_menuItem_izquierda"));
				miCentrado.setText(r.getString("etiqueta_menuItem_centrado"));
				miDerecha.setText(r.getString("etiqueta_menuItem_derecha"));
				miJustificado.setText(r.getString("etiqueta_menuItem_justificado"));
				miColorFuente.setText(r.getString("etiqueta_menuItem_color"));
				miAcerca.setText(r.getString("etiqueta_menuItem_acerca"));
				miEspaniol.setText(r.getString("etiqueta_menuItem_espaniol"));
				miIngles.setText(r.getString("etiqueta_menuItem_england"));
				btnAbrir.setToolTipText(r.getString("etiqueta_tooltip_abrir"));
				btnGuardar.setToolTipText(r.getString("etiqueta_tooltip_guardar"));
				btnSalir.setToolTipText(r.getString("etiqueta_tooltip_salir"));
				lbFuente.setText(r.getString("etiqueta_label_fuente"));
				comboFuente.setToolTipText(r.getString("etiqueta_tooltip_fuente"));
				lbTamanio.setText(r.getString("etiqueta_label_tamanio"));
				comboSize.setToolTipText(r.getString("etiqueta_tooltip_tamanio"));
				btnNegrita.setToolTipText(r.getString("etiqueta_tooltip_negrita"));
				btnCursiva.setToolTipText(r.getString("etiqueta_tooltip_cursiva"));
				btnSubrayado.setToolTipText(r.getString("etiqueta_tooltip_subrayado"));
				btnIzquierda.setToolTipText(r.getString("etiqueta_tooltip_izquierda"));
				btnCentrado.setToolTipText(r.getString("etiqueta_tooltip_centrado"));
				btnDerecha.setToolTipText(r.getString("etiqueta_tooltip_derecha"));
				btnJustificado.setToolTipText(r.getString("etiqueta_tooltip_justificado"));
				btnColor.setToolTipText(r.getString("etiqueta_tooltip_color"));
				btnSpain.setToolTipText(r.getString("etiqueta_tooltip_espaniol"));
				btnEngland.setToolTipText(r.getString("etiqueta_tooltip_england"));
				lbSkin.setText(r.getString("etiqueta_label_skin"));
				comboSkin.setToolTipText(r.getString("etiqueta_tooltip_skin"));
				popCortar.setText(r.getString("etiqueta_pop_cortar"));
				popCopiar.setText(r.getString("etiqueta_pop_copiar"));
				popPegar.setText(r.getString("etiqueta_pop_pegar"));
				popNegrita.setText(r.getString("etiqueta_pop_negrita"));
				popCursiva.setText(r.getString("etiqueta_pop_cursiva"));
				popSubrayado.setText(r.getString("etiqueta_pop_subrayado"));
				popIzquierda.setText(r.getString("etiqueta_pop_izquierda"));
				popCentrado.setText(r.getString("etiqueta_pop_centrado"));
				popDerecha.setText(r.getString("etiqueta_pop_derecha"));
				popJustificado.setText(r.getString("etiqueta_pop_justificado"));
			}
		});
		mIdioma.add(miIngles);
		
		//Añado la barra de herramientas con sus componentes
		toolbarra.setBounds(1, 31, 1192, 50);
		toolbarra.setFloatable(false);
		add(toolbarra);
		
		btnAbrir.setIcon(new ImageIcon(getClass().getResource("/resources/open.png")));
		btnAbrir.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_abrir"));
		btnAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser explorador = new JFileChooser();
				if (explorador.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					String ruta = explorador.getSelectedFile().getPath();
					try {
						BufferedReader reader = new BufferedReader(new FileReader(ruta));
						String textoTotal="";
						String linea = reader.readLine();
						while (linea != null) {
						textoTotal = textoTotal + linea + System.getProperty("line.separator");
						linea = reader.readLine();
						}
						area.setText(textoTotal);
						reader.close();
					}catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		});
		toolbarra.add(btnAbrir);
		btnGuardar.setIcon(new ImageIcon(getClass().getResource("/resources/save.png")));
		btnGuardar.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_guardar"));
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser explorador = new JFileChooser();
				if (JFileChooser.APPROVE_OPTION == explorador.showSaveDialog(null)) {
					String ruta = explorador.getSelectedFile().getPath();
					try {
						PrintWriter writer = new PrintWriter(ruta);
						writer.print(area.getText());
						writer.close();
					}catch (Exception e2) {
						System.out.println(e2.getMessage());
					}
				}
			}
		});
		toolbarra.add(btnGuardar);
		btnImagen.setIcon(new ImageIcon(getClass().getResource("/resources/image.png")));
		btnImagen.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_imagen"));
		btnImagen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
		        FileNameExtensionFilter picture = new FileNameExtensionFilter("JPEG files (*.png)", "png");
		        fc.setFileFilter(picture);
		        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		        if (JFileChooser.APPROVE_OPTION == fc.showSaveDialog(null)) {
			        String filename = fc.getSelectedFile().getAbsolutePath();
	
			        // Si no entra texto se refresca el dialogbox
			        if (filename == null) return;
	
			        try {
			            BufferedImage img = ImageIO.read(new File(filename));
			            ImageIcon pictureImage = new ImageIcon(img);
			            area.insertIcon(pictureImage);
			        } 
	
			        catch (IOException e3) {
			            JOptionPane.showMessageDialog(null, "Could not find file: " + filename);
			        }
		        }
			}
		});
		toolbarra.add(btnImagen);
		btnSalir.setIcon(new ImageIcon(getClass().getResource("/resources/exit.png")));
		btnSalir.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_salir"));
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String salir = ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_string_salir");
				int input = JOptionPane.showConfirmDialog(null, salir);
				if (input == 0) {
					System.exit(0);
				}
			}
		});
		toolbarra.add(btnSalir);
		toolbarra.addSeparator();
		
		for (String fuente : fuentes) {
			comboFuente.addItem(fuente);
		}
		
		toolbarra.add(lbFuente);
		toolbarra.addSeparator();
		comboFuente.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_fuente"));
		comboFuente.addActionListener(new StyledEditorKit.FontFamilyAction("Fuente", comboFuente.getSelectedItem().toString()));
		toolbarra.add(comboFuente);
		toolbarra.addSeparator();
		
		for (int i=10; i<=100; i+=2) {
			comboSize.addItem(i);
		}
		
		toolbarra.add(lbTamanio);
		toolbarra.addSeparator();
		comboSize.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_tamanio"));
		comboSize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int valor =  Integer.parseInt(comboSize.getSelectedItem().toString());
				Action fuente = new StyledEditorKit.FontSizeAction("Tamaño", valor);
				fuente.actionPerformed(e);
			}
		});
		toolbarra.add(comboSize);
		toolbarra.addSeparator();
		
		btnNegrita.setIcon(new ImageIcon(getClass().getResource("/resources/negrita.png")));
		btnNegrita.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_negrita"));
		btnNegrita.addActionListener(new StyledEditorKit.BoldAction());
		toolbarra.add(btnNegrita);
		btnCursiva.setIcon(new ImageIcon(getClass().getResource("/resources/cursiva.png")));
		btnCursiva.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_cursiva"));
		btnCursiva.addActionListener(new StyledEditorKit.ItalicAction());
		toolbarra.add(btnCursiva);
		btnSubrayado.setIcon(new ImageIcon(getClass().getResource("/resources/subrayado.png")));
		btnSubrayado.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_subrayado"));
		btnSubrayado.addActionListener(new StyledEditorKit.UnderlineAction());
		toolbarra.add(btnSubrayado);
		toolbarra.addSeparator();
		
		btnIzquierda.setIcon(new ImageIcon(getClass().getResource("/resources/izquierda.png")));
		btnIzquierda.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_izquierda"));
		btnIzquierda.addActionListener(new StyledEditorKit.AlignmentAction("Izquierda", 3));
		toolbarra.add(btnIzquierda);
		btnCentrado.setIcon(new ImageIcon(getClass().getResource("/resources/centrado.png")));
		btnCentrado.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_centrado"));
		btnCentrado.addActionListener(new StyledEditorKit.AlignmentAction("Centrado", 1));
		toolbarra.add(btnCentrado);
		btnDerecha.setIcon(new ImageIcon(getClass().getResource("/resources/derecha.png")));
		btnDerecha.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_derecha"));
		btnDerecha.addActionListener(new StyledEditorKit.AlignmentAction("Derecha", 2));
		toolbarra.add(btnDerecha);
		btnJustificado.setIcon(new ImageIcon(getClass().getResource("/resources/justificado.png")));
		btnJustificado.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_justificado"));
		btnJustificado.addActionListener(new StyledEditorKit.AlignmentAction("Justificado", 4));
		toolbarra.add(btnJustificado);
		toolbarra.addSeparator();
		
		btnColor.setIcon(new ImageIcon(getClass().getResource("/resources/color.png")));
		btnColor.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_color"));
		btnColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JColorChooser vColores = new JColorChooser();
				String mensaje = ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_string_color");
				Color color = vColores.showDialog(null, mensaje, Color.gray);
				
				int start = area.getSelectionStart();
				int end = area.getSelectionEnd();
				int selectedLength = end - start;
				
			    StyledDocument style = area.getStyledDocument();
			    AttributeSet oldSet = style.getCharacterElement(end-1).getAttributes();
			    StyleContext sc = StyleContext.getDefaultStyleContext();
			    AttributeSet s = sc.addAttribute(oldSet, StyleConstants.Foreground, color);
			    style.setCharacterAttributes(start, selectedLength, s, true);
			}
		});
		toolbarra.add(btnColor);
		toolbarra.addSeparator();
		
		btnSpain.setIcon(new ImageIcon(getClass().getResource("/resources/spain.png")));
		btnSpain.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_espaniol"));
		btnSpain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				ResourceBundle r = ResourceBundle.getBundle("etiquetas.Etiquetas");
				mArchivo.setText(r.getString("etiqueta_menu_archivo"));
				mFuente.setText(r.getString("etiqueta_menu_fuente"));
				mEstilo.setText(r.getString("etiqueta_menu_estilo"));
				mParrafo.setText(r.getString("etiqueta_menu_parrafo"));
				mTamanio.setText(r.getString("etiqueta_menu_tamanio"));
				mColor.setText(r.getString("etiqueta_menu_color"));
				mInformacion.setText(r.getString("etiqueta_menu_informacion"));
				mIdioma.setText(r.getString("etiqueta_menu_idioma"));
				miAbrir.setText(r.getString("etiqueta_menuItem_abrir"));
				miGuardar.setText(r.getString("etiqueta_menuItem_guardar"));
				miImagen.setText(r.getString("etiqueta_menuItem_imagen"));
				miSalir.setText(r.getString("etiqueta_menuItem_salir"));
				miNegrita.setText(r.getString("etiqueta_menuItem_negrita"));
				miCursiva.setText(r.getString("etiqueta_menuItem_cursiva"));
				miSubrayado.setText(r.getString("etiqueta_menuItem_subrayado"));
				miIzquierda.setText(r.getString("etiqueta_menuItem_izquierda"));
				miCentrado.setText(r.getString("etiqueta_menuItem_centrado"));
				miDerecha.setText(r.getString("etiqueta_menuItem_derecha"));
				miJustificado.setText(r.getString("etiqueta_menuItem_justificado"));
				miColorFuente.setText(r.getString("etiqueta_menuItem_color"));
				miAcerca.setText(r.getString("etiqueta_menuItem_acerca"));
				miEspaniol.setText(r.getString("etiqueta_menuItem_espaniol"));
				miIngles.setText(r.getString("etiqueta_menuItem_england"));
				btnAbrir.setToolTipText(r.getString("etiqueta_tooltip_abrir"));
				btnGuardar.setToolTipText(r.getString("etiqueta_tooltip_guardar"));
				btnSalir.setToolTipText(r.getString("etiqueta_tooltip_salir"));
				lbFuente.setText(r.getString("etiqueta_label_fuente"));
				comboFuente.setToolTipText(r.getString("etiqueta_tooltip_fuente"));
				lbTamanio.setText(r.getString("etiqueta_label_tamanio"));
				comboSize.setToolTipText(r.getString("etiqueta_tooltip_tamanio"));
				btnNegrita.setToolTipText(r.getString("etiqueta_tooltip_negrita"));
				btnCursiva.setToolTipText(r.getString("etiqueta_tooltip_cursiva"));
				btnSubrayado.setToolTipText(r.getString("etiqueta_tooltip_subrayado"));
				btnIzquierda.setToolTipText(r.getString("etiqueta_tooltip_izquierda"));
				btnCentrado.setToolTipText(r.getString("etiqueta_tooltip_centrado"));
				btnDerecha.setToolTipText(r.getString("etiqueta_tooltip_derecha"));
				btnJustificado.setToolTipText(r.getString("etiqueta_tooltip_justificado"));
				btnColor.setToolTipText(r.getString("etiqueta_tooltip_color"));
				btnSpain.setToolTipText(r.getString("etiqueta_tooltip_espaniol"));
				btnEngland.setToolTipText(r.getString("etiqueta_tooltip_england"));
				lbSkin.setText(r.getString("etiqueta_label_skin"));
				comboSkin.setToolTipText(r.getString("etiqueta_tooltip_skin"));
				popCortar.setText(r.getString("etiqueta_pop_cortar"));
				popCopiar.setText(r.getString("etiqueta_pop_copiar"));
				popPegar.setText(r.getString("etiqueta_pop_pegar"));
				popNegrita.setText(r.getString("etiqueta_pop_negrita"));
				popCursiva.setText(r.getString("etiqueta_pop_cursiva"));
				popSubrayado.setText(r.getString("etiqueta_pop_subrayado"));
				popIzquierda.setText(r.getString("etiqueta_pop_izquierda"));
				popCentrado.setText(r.getString("etiqueta_pop_centrado"));
				popDerecha.setText(r.getString("etiqueta_pop_derecha"));
				popJustificado.setText(r.getString("etiqueta_pop_justificado"));
			}
		});
		toolbarra.add(btnSpain);
		btnEngland.setIcon(new ImageIcon(getClass().getResource("/resources/england.png")));
		btnEngland.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_england"));
		btnEngland.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en"));
				ResourceBundle r = ResourceBundle.getBundle("etiquetas.Etiquetas");
				mArchivo.setText(r.getString("etiqueta_menu_archivo"));
				mFuente.setText(r.getString("etiqueta_menu_fuente"));
				mEstilo.setText(r.getString("etiqueta_menu_estilo"));
				mParrafo.setText(r.getString("etiqueta_menu_parrafo"));
				mTamanio.setText(r.getString("etiqueta_menu_tamanio"));
				mColor.setText(r.getString("etiqueta_menu_color"));
				mInformacion.setText(r.getString("etiqueta_menu_informacion"));
				mIdioma.setText(r.getString("etiqueta_menu_idioma"));
				miAbrir.setText(r.getString("etiqueta_menuItem_abrir"));
				miGuardar.setText(r.getString("etiqueta_menuItem_guardar"));
				miImagen.setText(r.getString("etiqueta_menuItem_imagen"));
				miSalir.setText(r.getString("etiqueta_menuItem_salir"));
				miNegrita.setText(r.getString("etiqueta_menuItem_negrita"));
				miCursiva.setText(r.getString("etiqueta_menuItem_cursiva"));
				miSubrayado.setText(r.getString("etiqueta_menuItem_subrayado"));
				miIzquierda.setText(r.getString("etiqueta_menuItem_izquierda"));
				miCentrado.setText(r.getString("etiqueta_menuItem_centrado"));
				miDerecha.setText(r.getString("etiqueta_menuItem_derecha"));
				miJustificado.setText(r.getString("etiqueta_menuItem_justificado"));
				miColorFuente.setText(r.getString("etiqueta_menuItem_color"));
				miAcerca.setText(r.getString("etiqueta_menuItem_acerca"));
				miEspaniol.setText(r.getString("etiqueta_menuItem_espaniol"));
				miIngles.setText(r.getString("etiqueta_menuItem_england"));
				btnAbrir.setToolTipText(r.getString("etiqueta_tooltip_abrir"));
				btnGuardar.setToolTipText(r.getString("etiqueta_tooltip_guardar"));
				btnSalir.setToolTipText(r.getString("etiqueta_tooltip_salir"));
				lbFuente.setText(r.getString("etiqueta_label_fuente"));
				comboFuente.setToolTipText(r.getString("etiqueta_tooltip_fuente"));
				lbTamanio.setText(r.getString("etiqueta_label_tamanio"));
				comboSize.setToolTipText(r.getString("etiqueta_tooltip_tamanio"));
				btnNegrita.setToolTipText(r.getString("etiqueta_tooltip_negrita"));
				btnCursiva.setToolTipText(r.getString("etiqueta_tooltip_cursiva"));
				btnSubrayado.setToolTipText(r.getString("etiqueta_tooltip_subrayado"));
				btnIzquierda.setToolTipText(r.getString("etiqueta_tooltip_izquierda"));
				btnCentrado.setToolTipText(r.getString("etiqueta_tooltip_centrado"));
				btnDerecha.setToolTipText(r.getString("etiqueta_tooltip_derecha"));
				btnJustificado.setToolTipText(r.getString("etiqueta_tooltip_justificado"));
				btnColor.setToolTipText(r.getString("etiqueta_tooltip_color"));
				btnSpain.setToolTipText(r.getString("etiqueta_tooltip_espaniol"));
				btnEngland.setToolTipText(r.getString("etiqueta_tooltip_england"));
				lbSkin.setText(r.getString("etiqueta_label_skin"));
				comboSkin.setToolTipText(r.getString("etiqueta_tooltip_skin"));
				popCortar.setText(r.getString("etiqueta_pop_cortar"));
				popCopiar.setText(r.getString("etiqueta_pop_copiar"));
				popPegar.setText(r.getString("etiqueta_pop_pegar"));
				popNegrita.setText(r.getString("etiqueta_pop_negrita"));
				popCursiva.setText(r.getString("etiqueta_pop_cursiva"));
				popSubrayado.setText(r.getString("etiqueta_pop_subrayado"));
				popIzquierda.setText(r.getString("etiqueta_pop_izquierda"));
				popCentrado.setText(r.getString("etiqueta_pop_centrado"));
				popDerecha.setText(r.getString("etiqueta_pop_derecha"));
				popJustificado.setText(r.getString("etiqueta_pop_justificado"));
			}
		});
		toolbarra.add(btnEngland);
		toolbarra.addSeparator();
		
		toolbarra.add(lbSkin);
		toolbarra.addSeparator();
		
		
		comboSkin.addItem("Noir");
		comboSkin.addItem("Arcy");
		comboSkin.addItem("Texture");
		comboSkin.addItem("Smart");
		comboSkin.setToolTipText(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_tooltip_skin"));
		comboSkin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String t = (String) comboSkin.getSelectedItem();
				try {
					com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Green", "INSERT YOUR LICENSE KEY HERE", "Company");
				
					if ("Noir".equals(t)) {
						UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
						SwingUtilities.updateComponentTreeUI(getParent());
						((Window) getParent()).pack();
					}
					else if ("Arcy".equals(t)) {
						UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
						SwingUtilities.updateComponentTreeUI(getParent());
						((Window) getParent()).pack();
					}
					else if ("Texture".equals(t)) {
						UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
						SwingUtilities.updateComponentTreeUI(getParent());
						((Window) getParent()).pack();
					}
					else {
						UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
						SwingUtilities.updateComponentTreeUI(getParent());
						((Window) getParent()).pack();
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		toolbarra.add(comboSkin);
		toolbarra.addSeparator();

		//Añado el scroll pane (incluye el text pane)
		sp1.setBounds(0, 81, 1195, 650);
		add(sp1);
	    
		//Añado el menú contextual con sus elementos al scroll pane
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		mContextual.add(popCortar = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_pop_cortar"), new ImageIcon(getClass().getResource("/resources/cortar.png"))));
		popCortar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String select = area.getSelectedText();
				StringSelection data = new StringSelection(select);
				clip.setContents(data, data);
				area.replaceSelection("");
			}
		});
		mContextual.add(popCopiar = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_pop_copiar"), new ImageIcon(getClass().getResource("/resources/copiar.png"))));
		popCopiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String select = area.getSelectedText();
				StringSelection data = new StringSelection(select);
				clip.setContents(data, data);
			}
		});
		mContextual.add(popPegar = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_pop_pegar"), new ImageIcon(getClass().getResource("/resources/pegar.png"))));
		popPegar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Transferable clipdata = clip.getContents(clip);
				try {
					if (clipdata.isDataFlavorSupported(DataFlavor.stringFlavor)) {
						String s = (String) (clipdata.getTransferData(DataFlavor.stringFlavor));
						area.replaceSelection(s);
					}
					else if (clipdata.isDataFlavorSupported(DataFlavor.imageFlavor)) {
						String ima = (String) (clipdata.getTransferData(DataFlavor.imageFlavor));
						area.replaceSelection(ima);
					}
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		mContextual.addSeparator();
		
		mContextual.add(popNegrita = new JMenuItem(miNegrita.getText(), miNegrita.getIcon()));
		popNegrita.addActionListener(new StyledEditorKit.BoldAction());
		mContextual.add(popCursiva = new JMenuItem(miCursiva.getText(), miCursiva.getIcon()));
		popCursiva.addActionListener(new StyledEditorKit.ItalicAction());
		mContextual.add(popSubrayado = new JMenuItem(miSubrayado.getText(), miSubrayado.getIcon()));
		popSubrayado.addActionListener(new StyledEditorKit.UnderlineAction());
		mContextual.addSeparator();
		
		mContextual.add(popIzquierda = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_pop_izquierda"), btnIzquierda.getIcon()));
		popIzquierda.addActionListener(new StyledEditorKit.AlignmentAction("Izquierda", 3));
		mContextual.add(popCentrado = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_pop_centrado"), btnCentrado.getIcon()));
		popCentrado.addActionListener(new StyledEditorKit.AlignmentAction("Centrado", 1));
		mContextual.add(popDerecha = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_pop_derecha"), btnDerecha.getIcon()));
		popDerecha.addActionListener(new StyledEditorKit.AlignmentAction("Derecha", 2));
		mContextual.add(popJustificado = new JMenuItem(ResourceBundle.getBundle("etiquetas.Etiquetas").getString("etiqueta_pop_justificado"), btnJustificado.getIcon()));
		popJustificado.addActionListener(new StyledEditorKit.AlignmentAction("Justificado", 4));
		area.setComponentPopupMenu(mContextual);
	}
}