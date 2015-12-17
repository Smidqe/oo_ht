package application.types;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class o_log_struct {
		public final StringProperty time;
		public final StringProperty text;
		public final StringProperty __from;
		public final StringProperty __to;
		
		
		public o_log_struct(String __time, String __text, String __from, String __to)
		{
			this.time = new SimpleStringProperty(__time);
			this.text = new SimpleStringProperty(__text);
			this.__from = new SimpleStringProperty(__from);
			this.__to = new SimpleStringProperty(__to);
		}


		public StringProperty timeProperty() {
			return time;
		}
		
		public StringProperty textProperty() {
			return text;
		}
		public StringProperty fromProperty() {
			return __from;
		}
		public StringProperty toProperty() {
			return __to;
		}
}
