/**
 *
 */
package org.stfc.message;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import org.stfc.dto.Users;

/**
 * @author dongdv
 *
 */
public class UsersResponse {

    @SerializedName("listUsers")
    private List<Users> listUsers;

    public List<Users> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<Users> listUsers) {
        this.listUsers = listUsers;
    }

}
