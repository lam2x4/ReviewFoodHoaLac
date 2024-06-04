document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('registerForm');

    form.addEventListener('submit', async (event) => {
        event.preventDefault();
        
        const password = document.getElementById('password').value;

        // Ensure bcryptjs is loaded and available
        if (typeof bcrypt !== 'undefined') {
            const saltRounds = 10;
            try {
                // Generate a salt and hash the password
                const hashedPassword = await bcrypt.hash(password, saltRounds);
                console.log('Hashed Password:', hashedPassword);
            } catch (error) {
                console.error('Error hashing password:', error);
            }

            // Optionally clear the input field
            form.reset();
        } else {
            console.error('bcrypt is not defined. Ensure bcryptjs is properly loaded.');
        }
    });
});
