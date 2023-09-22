class PotentialSolution {

    private String text;
    private double accuracy;

    public PotentialSolution(String _s) {
        text = _s.toUpperCase().trim();
    }

    public String getText() {
        return text;
    }
}