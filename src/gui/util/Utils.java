/**
 * 
 */
package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * @author ALLAN
 *
 */
public class Utils {

	public static Stage currentStage(ActionEvent actionEvent) {
		return (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
	}
	
	public static Integer tryParseInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
}
