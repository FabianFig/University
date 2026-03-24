import numpy as np
from sklearn.datasets import load_iris
from sklearn.preprocessing import OneHotEncoder
from sklearn.model_selection import train_test_split

np.random.seed(42)

# Load the Iris dataset
data = load_iris()
X = data.data # shape: (150, 4)
y = data.target.reshape(-1, 1) # reshape for encoder

# One-hot encode the labels
encoder = OneHotEncoder(sparse_output=False)
y_encoded = encoder.fit_transform(y) # shape: (150, 3)

# Normalize features (optional)
X = (X - np.mean(X, axis=0)) / np.std(X, axis=0)

# Split into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(
    X, y_encoded, test_size=0.2, random_state=42
)

# Set network dimensions
input_dim = 4  # number of features
hidden_dim = 6 # size of hidden layer
hidden_dim2 = 5 
output_dim = 3 # number of classes

# Initialize weights and biases
W1 = np.random.randn(4, 6)
b1 = np.random.randn(1, 6)
W2 = np.random.randn(6, 5)
b2 = np.random.randn(1, 5)
W3 = np.random.randn(5, 3)
b3 = np.random.randn(1, 3)

# Activation functions
def relu(a):
    return np.maximum(0, a)

def relu_derivative(a):
    return (a > 0).astype(float)

def softmax(a):
    exp_a = np.exp(a - np.max(a, axis=1, keepdims=True))
    return exp_a / np.sum(exp_a, axis=1, keepdims=True)

# Loss function: cross-entropy
def cross_entropy(y_pred, y_true):
    eps = 2e-10
    return -np.mean(np.sum(y_true * np.log(y_pred + eps), axis=1))

# Gradient of loss w.r.t. logits (output preactivation)
def dL_da3(y_pred, y_true):
    return y_pred - y_true

# Training loop
for epoch in range(10000):
    # Forward pass
    z1 = X_train @ W1 + b1  # preactivation hidden layer
    a1 = relu(z1)           # activation hidden layer
    z2 = a1 @ W2 + b2
    a2 = relu(z2)
    z3 = a2 @ W3 + b3       # preactivation output layer
    y_pred = softmax(z3)    # activation output layer (softmax)

    # Compute loss
    loss = cross_entropy(y_pred, y_train)

    # Backpropagation
    da3 = dL_da3(y_pred, y_train) # dL/da3
    dW3 = a2.T @ da3
    db3 = np.sum(da3, axis=0, keepdims=True)

    dz2 = da3 @ W3.T
    da2 = dz2 * relu_derivative(a2) 
    dW2 = a1.T @ da2 
    db2 = np.sum(da2, axis=0, keepdims=True)

    dz1 = da2 @ W2.T # dL/dz1
    da1 = dz1 * relu_derivative(a1) # dL/da1
    dW1 = X_train.T @ da1
    db1 = np.sum(da1, axis=0, keepdims=True)

    # Update parameters
    lr = 0.01 #Learning Rate
    W3 -= lr * dW3
    b3 -= lr * db3
    #Update W2
    W2 -= lr * dW2
    #Update b2
    b2 -= lr * db2
    #Update W1
    W1 -= lr * dW1
    #update b1
    b1 -= lr * db1

    # Print loss every 10 epochs
    if epoch % 1000 == 0:
        print(f"Epoch {epoch}, Loss: {loss:.4f}")

# Final predictions on test set
z1_test = X_test @ W1 + b1
a1_test = relu(z1_test)

z2_test = a1_test @ W2 + b2
a2_test = relu(z2_test)

z3_test = a2_test @ W3 + b3
y_test_pred = softmax(z3_test)

# Print predicted vs actual for comparison
print("Predicted classes:", np.argmax(y_test_pred[:5], axis=1))
print("Actual classes:", np.argmax(y_test[:5], axis=1))

print("\nFinal Predicted Output (first 5 samples):")
for row in y_pred[:5]:
    print([f"{val:.4f}" for val in row])

print("\nTarget Output (first 5 samples):")
print(y_test[:5])

correct_preds = np.argmax(y_test_pred, axis=1) == np.argmax(y_test, axis=1)
accuracy = np.mean(correct_preds)
print(f"\nTest Accuracy: {accuracy * 100:.2f}%")
