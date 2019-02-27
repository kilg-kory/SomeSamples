package ru.kilg.ss.run.annotate.cls;

class Sample {


    @SayIt
    private void sendAloha(String text) {
        if (text == null || text.isEmpty()) {
            text = "Aloha, Muchachas graces";
        }
        System.out.println(text);
    }

    @SayIt("Salut Ca va, Ces-ke tu fair")
    private void sendBonjur(String text) {
        if (text == null || text.isEmpty()) {
            text = "Je ne parle pa Franse";
        }
        System.out.println(text);
    }

}
