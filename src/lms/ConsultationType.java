package lms;

public enum ConsultationType {
	onlyConsultation{
        @Override
        public String toString() {
            return "only consultation";
        }
	},
	borrowing{
        @Override
        public String toString() {
            return "borrowing";
        }
	};
	
}
