
import com.nirali.sample.recipe.components.Resource
import kotlinx.serialization.Serializable



@Serializable
data class ErrorResponse(
    val success: Boolean,
    val message: String
)

