package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.ParagraphView;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

public class SRCTextPanel extends JPanel {
	
	private JTextPane textPane;
	private StyledDocument doc;
	
	public SRCTextPanel() {
		setPreferredSize(new Dimension(400, 200));
		setLayout(new BorderLayout());
		
//		text area
		textPane = new JTextPane();
		textPane.setEditable(false);
		
		//center text
		
//		SimpleAttributeSet center = new SimpleAttributeSet();
//		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
//		doc.setParagraphAttributes(0, doc.getLength(), center, true);
		
		// from: http://www.java2s.com/Tutorials/Java/Swing_How_to/JTextPane/Create_JEditorPane_vertical_alignment.htm
		textPane.setEditorKit(new MyEditorKit());
		doc = textPane.getStyledDocument();
		SimpleAttributeSet attrs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_CENTER);
		StyledDocument doc = (StyledDocument) textPane.getDocument();
		doc.setParagraphAttributes(0, doc.getLength() - 1, attrs, false);
		
//		border
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,40,70);
		setBorder(outerBorder);
		
		add(new JScrollPane(textPane), BorderLayout.CENTER);
	}
	
	
	
//// from http://javatechniques.com/blog/setting-jtextpane-font-and-color/
//    public static void setJTextPaneFont(JTextPane jtp, Font font, Color c) {
//        // Start with the current input attributes for the JTextPane. This
//        // should ensure that we do not wipe out any existing attributes
//        // (such as alignment or other paragraph attributes) currently
//        // set on the text area.
//        MutableAttributeSet attrs = jtp.getInputAttributes();
//
//        // Set the font family, size, and style, based on properties of
//        // the Font object. Note that JTextPane supports a number of
//        // character attributes beyond those supported by the Font class.
//        // For example, underline, strike-through, super- and sub-script.
//        StyleConstants.setFontFamily(attrs, font.getFamily());
//        StyleConstants.setFontSize(attrs, font.getSize());
//        StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);
//
//        // Set the font color
//        StyleConstants.setForeground(attrs, c);
//
//        // Retrieve the pane's document object
//        StyledDocument doc = jtp.getStyledDocument();
//
//        // Replace the style for the entire document. We exceed the length
//        // of the document by 1 so that text entered at the end of the
//        // document uses the attributes.
//        doc.setCharacterAttributes(0, doc.getLength() + 1, attrs, false);
//    }
//    
//    
//}




class MyEditorKit extends StyledEditorKit {

	  public ViewFactory getViewFactory() {
	    return new StyledViewFactory();
	  }

	  public class StyledViewFactory implements ViewFactory {

	    public View create(Element elem) {
	      String kind = elem.getName();
	      if (kind != null) {
	        if (kind.equals(AbstractDocument.ContentElementName)) {

	          return new LabelView(elem);
	        } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
	          return new ParagraphView(elem);
	        } else if (kind.equals(AbstractDocument.SectionElementName)) {

	          return new CenteredBoxView(elem, View.Y_AXIS);
	        } else if (kind.equals(StyleConstants.ComponentElementName)) {
	          return new ComponentView(elem);
	        } else if (kind.equals(StyleConstants.IconElementName)) {

	          return new IconView(elem);
	        }
	      }

	      return new LabelView(elem);
	    }

	  }
	}

class CenteredBoxView extends BoxView {
	  public CenteredBoxView(Element elem, int axis) {

	    super(elem, axis);
	  }

	  protected void layoutMajorAxis(int targetSpan, int axis, int[] offsets,
	      int[] spans) {

	    super.layoutMajorAxis(targetSpan, axis, offsets, spans);
	    int textBlockHeight = 0;
	    int offset = 0;

	    for (int i = 0; i < spans.length; i++) {
	      textBlockHeight += spans[i];
	    }
	    offset = (targetSpan - textBlockHeight) / 2;
	    for (int i = 0; i < offsets.length; i++) {
	      offsets[i] += offset;
	    }

	  }
	}
}

