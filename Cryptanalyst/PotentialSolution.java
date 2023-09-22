class PotentialSolution {

    private String text;
    private double accuracy;

    private static ArrayList<String> dictionary = null;
    public PotentialSolution(String _s) {
        text = _s.toUpperCase().trim();
    }
    private void calculateAccuracy() {
        if (dictionary == null) {
            initializeDictionary();
        }
        String[] toCheck = getText().split(" ");
        int totalWords = toCheck.length;
        int realWords = 0;
        for (String s : toCheck) {
            if (dictionary.contains(s)) {
                realWords++;
            }
        }
        accuracy = (double) realWords / (double) totalWords;
    }

    private static void initializeDictionary() {
    }

    public double getAccuracy() {
        return accuracy;
    }
    public String getText() {
        return text;
    }
}