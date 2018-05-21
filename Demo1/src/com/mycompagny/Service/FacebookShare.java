package com.mycompagny.Service;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
/**
 *
 * @author Fenina Malek
 */
public class FacebookShare {
    private static String token = "EAACEdEose0cBAOZCGiLnkgzjyHmryiV4S3K2PTl2pdR8rlM4fQVU4ZBIMqJGX451RggKcGSzarLZBVIVNbcnubbreb4SLopq57qirOcIMgZB8exwcn4qhUHpHjzeh0xQKAszXOiLu4FnCmbDU0XmNVm9xKerwhPT5jUB0sZA4Tjw82ZBZBz50InZAvZA74OVvWEMZD";

    public FacebookShare(String token) {
        FaceBookAccess.setToken(token);
    }
    
    public void share(String text) throws IOException {
	FaceBookAccess.getInstance().addResponseCodeListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent evt) {
		NetworkEvent ne = (NetworkEvent) evt;
		int code = ne.getResponseCode();
		FaceBookAccess.getInstance().removeResponseCodeListener(this);
	    }
	});
	FaceBookAccess.getInstance().postOnWall("me", text);
    }
    
}
