import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login.LoginNavigation
import kotlinx.serialization.Serializable

@Composable
fun MainNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginNavigationRoute
    ){
        composable<LoginNavigationRoute> {
            LoginNavigation()
        }
    }
}

@Serializable
object LoginNavigationRoute