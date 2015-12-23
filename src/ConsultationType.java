
public enum ConsultationType {
	onlineConsultation{
        @Override
        public String toString() {
            return "online consultation";
        }
	},
	borrowing{
        @Override
        public String toString() {
            return "borrowing";
        }
	};
	
}
