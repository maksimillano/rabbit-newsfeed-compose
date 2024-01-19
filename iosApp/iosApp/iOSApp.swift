import SwiftUI

@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
            ComposeViewControllerToSwiftUI()
                .ignoresSafeArea(.keyboard) // Compose have own keyboard handler
        }
    }
}
