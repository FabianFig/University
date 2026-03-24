import numpy as np
import matplotlib.pyplot as plt
from scipy.interpolate import interp1d

# Define basis functions {t·eᵗ, t²·e²ᵗ, ..., t⁶·e⁶ᵗ}
def basis_funcs(t, index):
    n = index + 1  # n = 1, 2, ..., 6
    return (t ** n) * np.exp(n * t)

# Generate noisy data (identical to original)
t = np.arange(0, np.pi + 0.1, 0.1)
clean_signal = np.cos(np.pi * t) * t
noise_level = np.std(clean_signal) / np.random.rand()
x = clean_signal + noise_level * np.random.randn(len(t))
x_interp = interp1d(t, x, kind='linear', bounds_error=False, fill_value="extrap")

# Compute Gram matrix A  (6×6) and vector b (6×1)
G = np.zeros((6, 6))
b = np.zeros(6)

for i in range(6):
    for j in range(6):
        # Inner product: A[i,j] = ∫ u_i(t) u_j(t) dt
        integrand = lambda t: basis_funcs(t, i) * basis_funcs(t, j)
        G[i, j] = np.trapz(integrand(t), t)
    
    # Projection: b[i] = ∫ x_interp(t) u_i(t) dt
    integrand_b = lambda t: x_interp(t) * basis_funcs(t, i)
    b[i] = np.trapz(integrand_b(t), t)

# Solve Ac = b for coefficients (required for projection)
coefficients = np.linalg.solve(G, b)

# Reconstruct approximation
approximation = np.zeros_like(t)
for i in range(6):
    approximation += coefficients[i] * basis_funcs(t, i)

# Plot results (same as original)
plt.figure(figsize=(10, 6))
plt.grid(True)
plt.plot(t, clean_signal, 'k--', linewidth=1.5, label='Clean Signal')
plt.plot(t, x, color=[0.6, 0.6, 0.6], linewidth=1, label='Noisy Data')
plt.plot(t, approximation, 'r-', linewidth=2, label='Projection Approximation')
plt.title('Synthetic Time Series with Trend and Noise Approximation using Basis {tⁿ·eⁿᵗ}')
plt.xlabel('Time (t)')
plt.ylabel('Value')
plt.legend(loc='best')
plt.show()