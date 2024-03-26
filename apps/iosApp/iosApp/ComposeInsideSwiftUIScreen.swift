import SwiftUI

struct ComposeInsideSwiftUIScreen: View {
    var body: some View {
        ZStack {
            ComposeLayer()
        }.onTapGesture {
            // Hide keyboard on tap outside of TextField
            UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
        }
    }
}

struct ComposeLayer: View {
    var body: some View {
        ComposeViewControllerToSwiftUI()
            .ignoresSafeArea(.keyboard) // Compose have own keyboard handler
    }
}
