import numpy as np
import matplotlib.pyplot as plt
np.set_printoptions(precision=4, suppress=True)

# --- Helper Functions ---
def is_valid_transition_matrix(A):
    A = np.array(A)
    if (A < 0).any():  # allow zeros, reject negatives
        return False
    col_sums = np.sum(A, axis=0)
    return np.allclose(col_sums, np.ones(A.shape[1]))

def is_probability_vector(x):
    x = np.array(x)
    if (x < 0).any():
        return False
    return np.isclose(np.sum(x), 1)

def build_google_matrix(A, p):
    n = A.shape[0]
    B = np.ones((n, n)) / n
    M = (1 - p) * A + p * B
    return M

# --- Main Program ---
def main():
    # 1. User Input
    A = np.zeros((3, 3))
    print("Enter the elements of matrix A row by row (3x3 matrix):")
    for i in range(3):
        for j in range(3):
            while True:
                try:
                    user_input = input(f"Enter A[{i+1},{j+1}]: ")
                    A[i, j] = float(eval(user_input))
                    break
                except:
                    print("Invalid input. Enter a number or fraction like 1/2.")

    x0 = np.zeros(3)
    print("Enter the 3 elements of starting vector x0:")
    for i in range(3):
        while True:
            try:
                user_input = input(f"Enter x0[{i+1}]: ")
                x0[i] = float(eval(user_input))
                break
            except:
                print("Invalid input. Enter a number or fraction like 1/2.")

    # 2. Validate Matrix and Starting Vector
    if not is_valid_transition_matrix(A):
        print("Error: A is not a valid transition matrix.")
        return

    if not is_probability_vector(x0):
        print("Error: x0 is not a valid probability vector.")
        return

    # 3. Iterative Computation
    N = 1000  # Number of iterations
    history = [x0.copy()]
    current_vector = x0.copy()

    for _ in range(N):
        next_vector = A @ current_vector
        assert is_probability_vector(next_vector)
        history.append(next_vector.copy())
        current_vector = next_vector

    x_infinity = current_vector

    print("\nApproximate x_infinity:", x_infinity)
    print("A x_infinity:", A @ x_infinity)

    # 4. Plot convergence
    norms = [np.linalg.norm(x_infinity - x) for x in history]
    plt.plot(norms)
    plt.xlabel('Iteration')
    plt.ylabel('Norm ||x_infinity - x_n||')
    plt.title('Convergence to x_infinity')
    plt.grid(True)
    plt.show()

    # 5. Eigenvalues and Eigenvectors
    eigenvalues, eigenvectors = np.linalg.eig(A)
    print("\nEigenvalues:", np.round(eigenvalues, 4))
    print("\nEigenvectors:\n", np.round(eigenvectors, 4))

    # Using Google Matrix
    use_google_matrix = input("\nDo you want to use the Google Matrix with teleportation? (yes/no): ").strip().lower()
    if use_google_matrix == 'yes':
        p = float(input("Enter the teleportation parameter p (0 <= p <= 1): "))
        if not (0 <= p <= 1):
            print("Error: p must be between 0 and 1.")
            return
        M = build_google_matrix(A, p)
        current_vector = x0.copy()
        history = [x0.copy()]
        for _ in range(N):
            next_vector = M @ current_vector
            assert is_probability_vector(next_vector)
            history.append(next_vector.copy())
            current_vector = next_vector
        x_infinity = current_vector
        print("\nUsing Google Matrix:")
        print("Approximate x_infinity:", x_infinity)
        print("M x_infinity:", M @ x_infinity)

if __name__ == "__main__":
    main()