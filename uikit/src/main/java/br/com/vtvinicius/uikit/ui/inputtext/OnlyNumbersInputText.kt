import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import br.com.vtvinicius.uikit.ui.inputtext.base.BaseInputText
import br.com.vtvinicius.uikit.ui.inputtext.base.InputTextState
import br.com.vtvinicius.uikit.ui.inputtext.utils.RegexEnum

@Composable
fun OnlyNumbersInputText(
    modifier: Modifier = Modifier,
    state: InputTextState = InputTextState.NORMAL,
    hint: String = "",
    maxLength: Int,
    onSearch: (String) -> Unit
) {
    state.getPasswordIcon(null)

    BaseInputText(
        modifier = modifier,
        hint = hint,
        state = state,
        mask = VisualTransformation.None,
        maxLength = maxLength,
        inputType = RegexEnum.NUMBERS,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        onSearch = onSearch
    )
}
//
//@Preview
//@Composable
//fun OnlyNumbersInputTextPreview() {
//    Box(
//        Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .padding(16.dp)
//    ) {
//        OnlyNumbersInputText(
//            state = InputTextState.NORMAL,
//            hint = "Campo Simples apenas Numeros",
//            maxLength = 100,
//            onSearch = {}
//        )
//    }
//}