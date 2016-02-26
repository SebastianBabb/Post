package Client;

import postgui.GPost;

public class PostBuilder {

    public static void main(String[] args) {
        final PostClient pc = PostClient.getInstance();
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GPost post_gui = new GPost();
                post_gui.init();
                post_gui.setPc(pc);
                post_gui.retrieveUPCList();
                post_gui.setVisible(true);
            }
        });
    }

}
