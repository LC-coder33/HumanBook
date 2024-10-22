package ebookFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ebookDAO.Ebdao;
import orderedDTO.OrderedDTO;

public class EbookFrame extends JFrame implements ActionListener, ItemListener {

	Ebdao edao = null;
	OrderedDTO odto = null;
	
	private JPanel ePane = new JPanel();
	
	public EbookFrame(Ebdao edao) {
		this.edao = edao;
		setBounds(100, 100, 1080, 720);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
